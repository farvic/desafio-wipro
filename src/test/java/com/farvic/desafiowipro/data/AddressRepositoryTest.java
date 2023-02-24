package com.farvic.desafiowipro.data;

import com.farvic.desafiowipro.domain.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    private AddressRepository addressRepositoryUnderTest;

    @Test
    void findNoAddress() {
        // Setup and Run the test
        Address address = addressRepositoryUnderTest.findByCep("01001-000");

        // Verify the results
        assertNull(address);
    }

    @Nested
    public class NestedTestBlock {
        Address expectedAddress;
        @BeforeEach
        void setUp() {
            expectedAddress = new Address("01001-000", "Praça da Sé", "lado ímpar", "Sé", "São Paulo", "SP");
            entityManager.persist(expectedAddress);
        }

        @Test
        void findAddress() {
            // Setup and Run the test
            Address address = addressRepositoryUnderTest.findByCep("01001-000");

            // Verify the results
            assertNotNull(address);
            assertEquals(address, expectedAddress);
        }

        @Test
        void saveAddress() {
            // Setup and Run the test
            Address address = addressRepositoryUnderTest.save(expectedAddress);

            // Verify the results
            assertNotNull(address);
            assertEquals(address, expectedAddress);
        }


    }


//    @Test
//    void testFindByCep() {
//        // Setup
//        // Run the test
//        addressRepositoryUnderTest.findByCep();
//
//        // Verify the results
//    }
}