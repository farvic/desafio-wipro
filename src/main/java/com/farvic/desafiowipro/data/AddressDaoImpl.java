package com.farvic.desafiowipro.data;

import com.farvic.desafiowipro.domain.Address;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;


@Repository
public class AddressDaoImpl implements AddressDao {

    private final RestTemplate restTemplate;

    public AddressDaoImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }
    @Override
    public Address findByCep(String cep) {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        return restTemplate.getForObject(url, Address.class);
    }
}
