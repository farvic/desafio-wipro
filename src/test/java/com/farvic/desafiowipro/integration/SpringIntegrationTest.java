package com.farvic.desafiowipro.integration;

import com.farvic.desafiowipro.DesafioWiproApplication;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;
import com.farvic.desafiowipro.errors.CustomControllerAdvice;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest(classes = DesafioWiproApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static Address latestResponse = null;

    @Autowired
    protected RestTemplate restTemplate;

        void executeTest() {
            AddressDto requestBody = new AddressDto("49095090");

            latestResponse = restTemplate.postForObject("http://localhost:8080/v1/consulta-endereco", requestBody, Address.class);

        }

}
