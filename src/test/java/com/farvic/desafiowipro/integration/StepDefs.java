package com.farvic.desafiowipro.integration;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StepDefs extends SpringIntegrationTest {

        @When("^the client sends his cep to /v1/consulta-endereco$")
        public void i_execute_the_test() {
            executeTest();
        }

        @Then("^the client receives the address$")
        public void i_should_get_the_response() {
            assertEquals("Aracaju", latestResponse.getCidade());
        }
}
