package com.farvic.desafiowipro.controllers;

import com.farvic.desafiowipro.domain.Address;
import com.farvic.desafiowipro.dtos.CepDto;
import com.farvic.desafiowipro.services.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@Tag(name = "Endereço", description = "API de Endereço")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // A validação do cep é feita através de uma anotação @Pattern no CepDto
    // em conjunto com a anotação @Valid no parâmetro do método.
    @Operation(summary = "Buscar endereço com cep",
            description = "Busca um endereço na API doViaCep através de um cep fornecido pelo usuário",
            tags = {"Endereço" })
    @ApiResponse(responseCode = "201", description = "OK")
    @PostMapping(value="/consulta-endereco", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Address> getShippingByCep(@Valid @RequestBody CepDto cep) {
        return ResponseEntity.ok(addressService.getShippingByCep(cep));
    }
}
