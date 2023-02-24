package com.farvic.desafiowipro.integration;

import com.farvic.desafiowipro.data.AddressRepository;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StepDefs extends SpringIntegrationTest {
    HttpClientErrorException errorResponseBody = null;
    String clientCep;
    @Given("the client sends a valid cep {string}")
    public void theClientSendsAValidCep(String arg0) {
        clientCep = arg0;
    }
    // CEP: 01001-000
    @When("making a request to \\/v1\\/consulta-endereco")
    public void i_execute_the_test() {
        executeTest(clientCep);
    }

    // Cidade: São Paulo
    @Then("the client should receive an address in {string}")
    public void i_should_get_the_response(String cidade) {
        Address address = response.getBody();
        assert address != null;
        assertEquals(cidade, address.getCidade());
    }

    // Usei double no teste, pois o cucumber não aceita BigDecimal e não haveria prejuízo
    // para o valor do frete de São Paulo (7.85).
    @And("the shipping value should be {double}")
    public void theShippingValueShouldBe(double shipping) {
        Address address = response.getBody();
        assert address != null;
        assertEquals(shipping, address.getValorFrete().doubleValue());
    }

    // Fluxo cep inválido

    @Given("the client sends an invalid cep {string}")
    public void theClientSendsAnInvalidCep(String invalidCep) {
        clientCep = invalidCep;
    }
    // CEP: 01000-000
    @When("making a request to \\/v1\\/consulta-frete")
    public void theClientSendsAnInvalidCepToVConsultaFrete() {
        errorResponseBody = assertThrows(HttpClientErrorException.class, () -> {
            invalidCepTest(clientCep);
        });

    }
    @Then("the client should receive an error {int}")
    public void theClientShouldReceiveAnErrorMessage(int errorCode) {
        assertEquals(errorCode, errorResponse.getStatusCodeValue());
    }



}
