package com.farvic.desafiowipro.domain;

import com.farvic.desafiowipro.utils.MoneySerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.persistence.*;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)


@Entity
@Tag(name = "Endereço")
@Table(name = "address")
public class Address implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(unique = true)
    @Pattern(regexp = "^(0[1-9]\\d{3}|[1-9]\\d{4})-?\\d{3}$")
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
    private BigDecimal valorFrete;



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

    @Override
    public int hashCode() {
        return Objects.hash(getCep(), getRua(), getComplemento(), getBairro(), getCidade(), getEstado(), getValorFrete());
    }

    public Address(String cep, String rua, String complemento, String bairro, String cidade, String estado, BigDecimal valorFrete) {
        this.cep = cep;
        this.rua = rua;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.valorFrete = valorFrete;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public BigDecimal getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(BigDecimal valorFrete) {
        this.valorFrete = valorFrete;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getCep(), address.getCep()) && Objects.equals(getRua(), address.getRua()) && Objects.equals(getComplemento(), address.getComplemento()) && Objects.equals(getBairro(), address.getBairro()) && Objects.equals(getCidade(), address.getCidade()) && Objects.equals(getEstado(), address.getEstado()) && Objects.equals(getValorFrete(), address.getValorFrete());
    }


}
