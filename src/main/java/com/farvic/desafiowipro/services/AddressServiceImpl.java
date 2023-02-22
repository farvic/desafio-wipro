package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.domain.AddressDto;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.farvic.desafiowipro.utils.CepRegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Validator;
import java.util.logging.Logger;



@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private Validator validator;

    final Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());

    private final RestTemplate restTemplate;

    public AddressServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Address findAddressByCep(AddressDto cep) {

        String cepString = cep.getCep();
        String url = "https://viacep.com.br/ws/" + cepString + "/json/";
        Address address = new Address();

        if(cepString == null || !cepString.matches("^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")){
            throw new AddressNotFoundException("CEP inválido. Insira um cep no formato 01001000 ou 01001-000");
        }

        address = restTemplate.getForObject(url, Address.class);
        assert address != null;

        if(address.getCep() == null){
            LOGGER.info("findAddressByCep: O CEP não foi encontrado");
            throw new AddressNotFoundException("O CEP não foi encontrado na nossa base de dados. Pedimos desculpas pelo transtorno.");
        }

        String region = String.valueOf(CepRegionMapper.getRegion(address.getCep()));
        LOGGER.info("findAddressByCep: Região do CEP: " + region);

        return address;

    }
}
