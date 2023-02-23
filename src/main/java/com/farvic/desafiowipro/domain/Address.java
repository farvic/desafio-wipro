package com.farvic.desafiowipro.domain;

import com.farvic.desafiowipro.utils.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)


public class Address implements Serializable {
    @Pattern(regexp = "^\\d{5}-?\\d{3}$",
            message = "CEP inválido. Insira um cep no formato 01001000 ou 01001-000")
    private String cep;


    @Column
    @JsonProperty("logradouro")
    private String rua;

    @Column
    private String complemento;

    @Column
    private String bairro;
    @Column
    @JsonProperty("localidade")
    private String cidade;

    @Column
    @JsonProperty("uf")
    private String estado;

    @Column
    @JsonProperty("frete")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal shippingValue;



    public Address() {

    }


    public Address(String cep, String rua, String complemento, String bairro, String cidade, String estado) {
        this.cep = cep;
        this.rua = rua;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getShippingValue() {
        return shippingValue;
    }

    public void setShippingValue(BigDecimal shippingValue) {
        this.shippingValue = shippingValue;
    }

    @Override
    public String toString() {
        return "Address{" +
                "cep='" + cep + '\'' +
                ", rua='" + rua + '\'' +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getCep().equals(address.getCep()) && getRua().equals(address.getRua()) && getComplemento().equals(address.getComplemento()) && getBairro().equals(address.getBairro()) && getCidade().equals(address.getCidade()) && getEstado().equals(address.getEstado());
    }

}
