package com.farvic.desafiowipro.services;

import com.farvic.desafiowipro.data.AddressDaoImpl;
import com.farvic.desafiowipro.data.AddressRepository;
import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;
import com.farvic.desafiowipro.dtos.CepDto;
import com.farvic.desafiowipro.errors.AddressNotFoundException;
import com.farvic.desafiowipro.utils.AddressDtoMapper;
import com.farvic.desafiowipro.utils.CepUtils;
import com.farvic.desafiowipro.utils.Region;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Service
public class AddressServiceImpl implements AddressService {
//    @Autowired
    private final AddressRepository addressRepository;
//    @Autowired
    private final AddressDaoImpl enderecoDaoImpl;

    final static Logger LOGGER = Logger.getLogger(AddressServiceImpl.class.getName());

    public AddressServiceImpl(AddressRepository addressRepository, AddressDaoImpl enderecoDaoImpl) {
        this.addressRepository = addressRepository;
        this.enderecoDaoImpl = enderecoDaoImpl;
    }


    @Override
    public Address getShippingByCep(CepDto cep) {

        AddressDto addressResponse;
        Address address;
        BigDecimal shippingValue;
        Region region;
        String cepString;

        // Formata o CEP para o padrão 00000-000, evitando salvar duas vezes o mesmo CEP na base de dados.

        cepString = CepUtils.formatCep(cep.getCep());

        address = addressRepository.findByCep(cepString);

        if(address != null){
            LOGGER.info("O CEP já está cadastrado na nossa base de dados.");
            return address;
        }

        addressResponse =enderecoDaoImpl.findByCep(cepString);

        if(addressResponse.getCep() == null){
            throw new AddressNotFoundException("O CEP não foi encontrado na nossa base de dados. Pedimos desculpas pelo transtorno.");
        }

        region = Region.getRegionByState(addressResponse.getState());
        shippingValue = region.getShippingValue();

        address = AddressDtoMapper.toEntity(addressResponse);

        address.setShipping(shippingValue);

        addressRepository.save(address);

        return address;
    }
}
