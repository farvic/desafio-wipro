package com.farvic.desafiowipro.data;

import com.farvic.desafiowipro.domain.Address;

public interface AddressDao {
    Address findByCep(String cep);

}
