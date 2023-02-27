package com.farvic.desafiowipro.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.constraints.Pattern;
import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)


public class AddressDto implements Serializable {

    @Pattern(regexp = "^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")
    private String cep;



    @JsonProperty("logradouro")
    private String street;

    @JsonProperty("complemento")
    private String complement;


    @JsonProperty("bairro")
    private String neighborhood;

    @JsonProperty("localidade")
    private String city;

    @JsonProperty("uf")
    private String state;




    public AddressDto() {

    }

    public AddressDto(String cep, String street, String complement, String neighborhood, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
