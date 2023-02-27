package com.farvic.desafiowipro.domain;

import com.farvic.desafiowipro.dtos.CepDto;
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
class CepDtoTest {

    private Validator validator;

    @BeforeAll
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testGetAddress() {
        // Setup
        CepDto cepDto = new CepDto("01001-000");

        // Run the test
        final String result = cepDto.getCep();

        // Verify the results
        assertEquals("01001-000", result);
    }

    @Test
    void testSetAddress() {
        // Setup
        CepDto cepDto = new CepDto("01001-000");

        // Run the test
        cepDto.setCep("01001-555");

        // Verify the results
        assertEquals("01001-555", cepDto.getCep());
    }

    @Test
    void testValidCep() {
        // Setup
        CepDto cepDto = new CepDto("01001-000");
        // Run the test
        Set<ConstraintViolation<CepDto>> violations = validator.validate(cepDto);

        // Verify the results
        assertEquals("01001-000", cepDto.getCep());
        assertTrue(violations.isEmpty());
    }

    @Test
    void testValidCepWithNumbers() {

        // Setup
        CepDto cepDto = new CepDto("01001000");

        // Run the test
        Set<ConstraintViolation<CepDto>> violations = validator.validate(cepDto);

        // Verify the results
        assertEquals("01001000", cepDto.getCep());
        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidCep() {
        // Setup
        CepDto address = new CepDto("0100-1000");

        // Run the test
        Set<ConstraintViolation<CepDto>> violations = validator.validate(address);

        // Verify the results
        assertFalse(violations.isEmpty());
        assertEquals("0100-1000", address.getCep());
    }


}
