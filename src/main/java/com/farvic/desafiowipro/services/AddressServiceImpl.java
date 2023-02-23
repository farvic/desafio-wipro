package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.data.AddressRepositoryImpl;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.domain.AddressDto;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.farvic.desafiowipro.utils.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.TreeMap;
import java.util.logging.Logger;


@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private Validator validator;


    private AddressRepositoryImpl addressRepositoryImpl;


    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());


    public AddressServiceImpl(AddressRepositoryImpl addressRepositoryImpl) {
        this.addressRepositoryImpl = addressRepositoryImpl;
    }

    @Override
    public Address getShippingByCep(AddressDto cep) {

        Address address;
        String cepString = cep.getCep();

        if(cepString == null || !cepString.matches("^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")){
            throw new AddressNotFoundException("CEP inválido. Insira um cep no formato 01001000 ou 01001-000");
        }

        address = addressRepositoryImpl.findByCep(cepString);

        if(address.getCep() == null){
            LOGGER.info("findAddressByCep: O CEP não foi encontrado");
            throw new AddressNotFoundException("O CEP não foi encontrado na nossa base de dados. Pedimos desculpas pelo transtorno.");
        }

        // O TreeMap permite uma busca mais eficiente pela região correspondente, uma vez que ela está
        // associada ao respectivo valor mínimo de CEP, que é a chave do TreeMap. A utilização do
        // floorEntry permite encontrar a entrada no mapa cuja chave é a maior dentre aquelas
        // menores ou iguais ao valor passado como argumento.

        // Leva-se em conta as discontinuidades de CEP que ocorrem no Centro-Oeste e Norte do país:
        // Centro-Oeste: 70000-000 a 76799-999, 78000-000 a 79999-999
        // Norte: 66000-000 a 69999-999, 76800-000 a 77999-999

        int firstFiveDigits = Integer.parseInt(cep.getCep().substring(0,5));

        TreeMap<Integer, Region> cepMap = new TreeMap<>();
        cepMap.put(1000, Region.SUDESTE);                   // Sudeste: 01000-000 a 39999-999
        cepMap.put(40000, Region.NORDESTE);                 // Nordeste: 40000-000 a 65999-999
        cepMap.put(66000, Region.NORTE);                    // Norte: 66000-000 a 69999-999, 76800-000 a 77999-999
        cepMap.put(70000, Region.CENTRO);                   // Centro-Oeste: 70000-000 a 76799-999, 78000-000 a 79999-999
        cepMap.put(76800, Region.NORTE);
        cepMap.put(78000, Region.CENTRO);
        cepMap.put(80000, Region.SUL);                      // Sul: 80000-000 a 99999-999
        
        Region region = cepMap.floorEntry(firstFiveDigits).getValue();
        BigDecimal shippingValue = region.getShippingValue();

        address.setShippingValue(shippingValue);

        LOGGER.info("O frete para o cep" + cep.getCep() + " é de R$" + shippingValue);

        return address;

    }
}
