package com.farvic.desafiowipro.data;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.farvic.desafiowipro.services.AddressServiceImpl;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;


@Repository
public class AddressRepositoryImpl implements AddressRepository {

    private final RestTemplate restTemplate;



    public AddressRepositoryImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public Address findByCep(String cep) {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        return restTemplate.getForObject(url, Address.class);

    }
}
