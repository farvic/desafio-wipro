package com.farvic.desafiowipro.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.io.Serializable;

public class CepDto implements Serializable {

    @JsonProperty("cep")
    @NotNull
    @Pattern(regexp = "^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")
    private String cep;

    public CepDto() {
    }

    public CepDto(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
