package com.farvic.desafiowipro.data;

import com.farvic.desafiowipro.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByCep(String cep);

}
