package com.farvic.desafiowipro.utils;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddressDtoMapperTest {

    private AddressDtoMapper addressDtoMapperUnderTest;

    @BeforeEach
    void setUp() {
        addressDtoMapperUnderTest = new AddressDtoMapper();
    }

    @Test
    void testToDto() {
        // Setup
        final Address address = new Address("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP", new BigDecimal("7.85"));

        // Run the test
        final AddressDto result = addressDtoMapperUnderTest.toDto(address);

        // Verify the results
        assertTrue(AddressDtoMapper.isEqualTo(result, address));
    }

    @Test
    void testToEntity() {
        // Setup
        final AddressDto addressDto = new AddressDto("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
        final Address expectedResult = new Address("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");

        // Run the test
        final Address result = AddressDtoMapper.toEntity(addressDto);

        // Verify the results
        assertTrue(AddressDtoMapper.isEqualTo(addressDto, result));

    }
}
