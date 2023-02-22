package com.farvic.desafiowipro.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Pattern;

import java.io.Serializable;

public class AddressDto implements Serializable {

    @JsonProperty("cep")
    @Pattern(regexp = "^\\d{5}-?\\d{3}$", message = "CEP inválido. Insira um cep no formato 01001000 ou 01001-000")
    private String cep;

    public AddressDto() {
    }

    public AddressDto(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
