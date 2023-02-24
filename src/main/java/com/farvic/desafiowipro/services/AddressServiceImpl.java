package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.data.AddressDaoImpl;
import com.farvic.desafiowipro.data.AddressRepository;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.farvic.desafiowipro.utils.CepUtils;
import com.farvic.desafiowipro.utils.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;
    private final AddressDaoImpl enderecoDaoImpl;
    public AddressServiceImpl(AddressDaoImpl enderecoDaoImpl) {
        this.enderecoDaoImpl = enderecoDaoImpl;
    }

    @Override
    public Address getShippingByCep(AddressDto cep) {

        Address address;
        BigDecimal shippingValue;
        Region region;
        String cepString;

        cepString = CepUtils.formatCep(cep.getCep());

        address = addressRepository.findByCep(cepString);

        if(address != null){
            return address;
        }

        address =enderecoDaoImpl.findByCep(cepString);

        if(address.getCep() == null){
            throw new AddressNotFoundException("O CEP não foi encontrado na nossa base de dados. Pedimos desculpas pelo transtorno.");
        }

        region = Region.getRegionByCep(cepString);
        shippingValue = region.getShippingValue();

        address.setValorFrete(shippingValue);

        addressRepository.save(address);

        return address;
    }
}
