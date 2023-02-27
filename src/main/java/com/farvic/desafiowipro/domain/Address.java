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
    @JsonProperty("rua")
    private String street;

    @Column
    @JsonProperty("complemento")
    private String complement;

    @Column
    @JsonProperty("bairro")
    private String neighborhood;
    @Column
    @JsonProperty("cidade")
    private String city;

    @Column
    @JsonProperty("estado")
    private String state;


    @Column
    @JsonProperty("frete")
    @JsonSerialize(using = MoneySerializer.class)
    private BigDecimal shipping;



    public Address() {

    }

    public Address(String cep, String street, String complement, String neighborhood, String city, String state, BigDecimal shipping) {
        this.cep = cep;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.shipping = shipping;
    }

    public Address(String cep, String street, String complement, String neighborhood, String city, String state) {
        this.cep = cep;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
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

    public BigDecimal getShipping() {
        return shipping;
    }

    public void setShipping(BigDecimal shipping) {
        this.shipping = shipping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getCep().equals(address.getCep()) && Objects.equals(getStreet(), address.getStreet()) && Objects.equals(getComplement(), address.getComplement()) && getNeighborhood().equals(address.getNeighborhood()) && getCity().equals(address.getCity()) && getState().equals(address.getState()) && getShipping().equals(address.getShipping());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCep(), getStreet(), getComplement(), getNeighborhood(), getCity(), getState(), getShipping());
    }
}
