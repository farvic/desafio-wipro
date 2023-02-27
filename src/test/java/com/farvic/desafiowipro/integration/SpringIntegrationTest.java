package com.farvic.desafiowipro.integration;

import com.farvic.desafiowipro.DesafioWiproApplication;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.CepDto;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@CucumberContextConfiguration
@SpringBootTest(classes = DesafioWiproApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringIntegrationTest {
    static ResponseEntity<Address> response = null;
    ResponseEntity<Address> errorResponse = new ResponseEntity<Address>(HttpStatus.BAD_REQUEST);

    @Autowired
    protected RestTemplate restTemplate;

        void executeTest(String cep) {
            CepDto requestBody = new CepDto(cep);

            response = restTemplate.postForEntity("http://localhost:8080/v1/consulta-endereco", requestBody, Address.class);

        }

        void invalidCepTest(String cep) {
            CepDto requestBody = new CepDto(cep);

            errorResponse = restTemplate.postForEntity("http://localhost:8080/v1/consulta-endereco", requestBody, Address.class);
        }

}
