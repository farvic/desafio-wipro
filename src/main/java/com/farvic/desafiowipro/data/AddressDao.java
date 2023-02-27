package com.farvic.desafiowipro.data;

import com.farvic.desafiowipro.dtos.AddressDto;

public interface AddressDao {
    AddressDto findByCep(String cep);

}
