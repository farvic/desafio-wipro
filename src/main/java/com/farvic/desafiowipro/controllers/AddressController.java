package com.farvic.desafiowipro.controllers;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.AddressDto;
import com.farvic.desafiowipro.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Tag(name = "Address", description = "Address API")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @Operation(summary = "Buscar endereço com cep",
            description = "Busca um endereço na API doViaCep através de um cep fornecido pelo usuário",
            tags = {"Address" })
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping(value="/consulta-endereco", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Address> getShippingByCep(@RequestBody AddressDto cep) {
        return ResponseEntity.ok(addressService.getShippingByCep(cep));
    }

}
