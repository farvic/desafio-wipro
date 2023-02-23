package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.data.AddressDaoImpl;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.farvic.desafiowipro.utils.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.logging.Logger;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private Validator validator;


    private final AddressDaoImpl enderecoDaoImpl;


    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());


    public AddressServiceImpl(AddressDaoImpl enderecoDaoImpl) {
        this.enderecoDaoImpl = enderecoDaoImpl;
    }

    @Override
    public Address getShippingByCep(AddressDto cep) {

        Address address;
        String cepString = cep.getCep();

        if(cepString == null || !cepString.matches("^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")){
            throw new AddressNotFoundException("CEP inválido. Insira um cep no formato 01001000 ou 01001-000");
        }

        address = enderecoDaoImpl.findByCep(cepString);

        if(address.getCep() == null){
            LOGGER.info("findAddressByCep: O CEP não foi encontrado");
            throw new AddressNotFoundException("O CEP não foi encontrado na nossa base de dados. Pedimos desculpas pelo transtorno.");
        }



        Region region = Region.getRegionByCep(cepString);
        BigDecimal shippingValue = region.getShippingValue();

        address.setValorFrete(shippingValue);

        LOGGER.info("O frete para o cep" + cep.getCep() + " é de R$" + shippingValue);

        return address;

    }
}
