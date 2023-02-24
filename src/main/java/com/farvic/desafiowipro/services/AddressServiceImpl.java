package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.data.AddressDaoImpl;
import com.farvic.desafiowipro.data.AddressRepository;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.farvic.desafiowipro.utils.CepUtils;
import com.farvic.desafiowipro.utils.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.logging.Logger;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private Validator validator;

    @Autowired
    private AddressRepository addressRepository;


    private final AddressDaoImpl enderecoDaoImpl;


    private static final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());


    public AddressServiceImpl(AddressDaoImpl enderecoDaoImpl) {
        this.enderecoDaoImpl = enderecoDaoImpl;
    }

    @Override
    public Address getShippingByCep(AddressDto cep) {

        Optional<Address> address;
        BigDecimal shippingValue;
        Region region;
        String cepString = cep.getCep();
        boolean save = false;

        if(cepString == null || !cepString.matches("^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")){
            throw new AddressNotFoundException("CEP inválido. Insira um cep no formato 01001000 ou 01001-000");
        }
        cepString = CepUtils.formatCep(cepString);

        address = addressRepository.findByCep(cepString);

        if(address.isEmpty()){
            address = Optional.ofNullable(enderecoDaoImpl.findByCep(cepString));
            save = true;
        }

        if(address.isEmpty()){
            LOGGER.info("findAddressByCep: O CEP não foi encontrado");
            throw new AddressNotFoundException("O CEP não foi encontrado na nossa base de dados. Pedimos desculpas pelo transtorno.");
        }

        region = Region.getRegionByCep(cepString);
        shippingValue = region.getShippingValue();

        address.get().setValorFrete(shippingValue);

        if(save){
            addressRepository.save(address.get());
        }

        LOGGER.info("O frete para o cep " + cepString + " é de R$" + shippingValue);

        return address.get();

    }
}
