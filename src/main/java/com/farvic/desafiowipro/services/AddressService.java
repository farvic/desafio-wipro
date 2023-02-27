package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.CepDto;

public interface AddressService {
    Address getShippingByCep(CepDto cep);

}
