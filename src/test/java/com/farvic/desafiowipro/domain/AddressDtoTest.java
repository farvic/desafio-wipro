package com.farvic.desafiowipro.domain;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AddressDtoTest {

    private Validator validator;

    @BeforeAll
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetAddress() {
        // Setup
        AddressDto addressDto = new AddressDto("01001-000");

        // Run the test
        final String result = addressDto.getCep();

        // Verify the results
        assertEquals("01001-000", result);
    }

    @Test
    void testSetAddress() {
        // Setup
        AddressDto addressDto = new AddressDto("01001-000");

        // Run the test
        addressDto.setCep("01001-555");

        // Verify the results
        assertEquals("01001-555", addressDto.getCep());
    }

    @Test
    void testValidCep() {
        // Setup
        AddressDto addressDto = new AddressDto("01001-000");
        // Run the test
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(addressDto);

        // Verify the results
        assertEquals("01001-000", addressDto.getCep());
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidCepWithNumbers() {

        // Setup
        AddressDto addressDto = new AddressDto("01001000");

        // Run the test
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(addressDto);

        // Verify the results
        assertEquals("01001000", addressDto.getCep());
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidCep() {
        // Setup
        AddressDto address = new AddressDto("0100-1000");

        // Run the test
        Set<ConstraintViolation<AddressDto>> violations = validator.validate(address);

        // Verify the results
        assertFalse(violations.isEmpty());
        assertEquals("0100-1000", address.getCep());
    }


}
