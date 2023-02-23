package com.farvic.desafiowipro.data;

import com.farvic.desafiowipro.domain.Address;

public interface AddressRepository {
    Address findByCep(String cep);

}
