package com.farvic.desafiowipro.utils;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;

public class AddressDtoMapper {

    public static Address toEntity(AddressDto addressDto) {
        Address address = new Address();
        address.setCep(addressDto.getCep());
        address.setStreet(addressDto.getStreet());
        address.setComplement(addressDto.getComplement());
        address.setNeighborhood(addressDto.getNeighborhood());
        address.setCity(addressDto.getCity());
        address.setState(addressDto.getState());
        return address;
    }

    public AddressDto toDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCep(address.getCep());
        addressDto.setStreet(address.getStreet());
        addressDto.setComplement(address.getComplement());
        addressDto.setNeighborhood(address.getNeighborhood());
        addressDto.setCity(address.getCity());
        addressDto.setState(address.getState());
        return addressDto;
    }

    public static boolean isEqualTo(AddressDto addressDto, Address address) {
        return addressDto.getCep().equals(address.getCep())
                && addressDto.getStreet().equals(address.getStreet())
                && addressDto.getComplement().equals(address.getComplement())
                && addressDto.getNeighborhood().equals(address.getNeighborhood())
                && addressDto.getCity().equals(address.getCity())
                && addressDto.getState().equals(address.getState());
    }


}
