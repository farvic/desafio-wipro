package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.domain.AddressDto;

public interface AddressService {
    Address getShippingByCep(AddressDto cep);

}
