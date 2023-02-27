package com.farvic.desafiowipro.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressTest {

    private Address addressUnderTest;
    private static Validator validator;

    @BeforeEach
    void setUp() {
        addressUnderTest = new Address("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", new BigDecimal("7.85"));
    }

    @BeforeAll
    static void setUpAll() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testAddressSetters() {
        // Setup
        Address sameAdress = new Address();
        sameAdress.setCep("01001-000");
        sameAdress.setStreet("Praça da Sé");
        sameAdress.setComplement("lado ímpar");
        sameAdress.setNeighborhood("Sé");
        sameAdress.setCity("São Paulo");
        sameAdress.setState("SP");
        sameAdress.setShipping(new BigDecimal("7.85"));


        // Verify the results
        assertTrue(addressUnderTest.equals(sameAdress));
    }

    @Test
    void testAddressGetters() {
        // Setup
        Address sameAdress = new Address();
        sameAdress.setCep("01001-000");
        sameAdress.setStreet("Praça da Sé");
        sameAdress.setComplement("lado ímpar");
        sameAdress.setNeighborhood("Sé");
        sameAdress.setCity("São Paulo");
        sameAdress.setState("SP");

        Set<ConstraintViolation<Address>> violations = validator.validate(addressUnderTest);

        // Verify the results
        assertTrue(violations.isEmpty());
        assertEquals(sameAdress.getCep(), addressUnderTest.getCep());
        assertEquals(sameAdress.getStreet(), addressUnderTest.getStreet());
        assertEquals(sameAdress.getComplement(), addressUnderTest.getComplement());
        assertEquals(sameAdress.getNeighborhood(), addressUnderTest.getNeighborhood());
        assertEquals(sameAdress.getCity(), addressUnderTest.getCity());
        assertEquals(sameAdress.getState(), addressUnderTest.getState());

    }



    @Test
    void testValidCep() {
        // Setup
        addressUnderTest.setCep("01001-555");
        // Run the test
        Set<ConstraintViolation<Address>> violations = validator.validate(addressUnderTest);

        // Verify the results
        assertEquals("01001-555", addressUnderTest.getCep());
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidCepWithNumbers() {

        // Setup
        addressUnderTest.setCep("01001000");

        // Run the test
        Set<ConstraintViolation<Address>> violations = validator.validate(addressUnderTest);

        // Verify the results
        assertEquals("01001000", addressUnderTest.getCep());
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidCep() {
        // Setup
        addressUnderTest.setCep("0100-1000");

        // Run the test
        Set<ConstraintViolation<Address>> violations = validator.validate(addressUnderTest);

        // Verify the results
        assertFalse(violations.isEmpty());
        assertEquals("0100-1000", addressUnderTest.getCep());
    }
//    @Test
//    void testToString() {
//        assertThat(addressUnderTest.toString()).isEqualTo("result");
//    }
}
