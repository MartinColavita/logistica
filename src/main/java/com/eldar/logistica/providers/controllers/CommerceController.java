package com.eldar.logistica.providers.controllers;

import com.eldar.logistica.providers.model.request.CommerceRequestDTO;
import com.eldar.logistica.providers.model.request.ReserveRequestDTO;
import com.eldar.logistica.providers.model.response.CommerceResponseDTO;
import com.eldar.logistica.providers.services.contracts.CommerceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@Tag(name = "Commerces", description = "API para administrar comercios")
@RestController
@RequestMapping("/api/commerces")
public class CommerceController {

    private final CommerceService commerceService;



    @GetMapping("/all")
    @Operation(
            summary = "Obtener todos los comercios",
            description = "Devuelve una lista de todos los comercios disponibles."
    )
    public ResponseEntity<List<CommerceResponseDTO>> getAllCommerces() {
        return new ResponseEntity<>(commerceService.getAllCommerces(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener un comercio por ID",
            description = "Devuelve un comercio según el ID proporcionado.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del comercio",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<CommerceResponseDTO> getCommerceById(@PathVariable Long id) {
        return new ResponseEntity<>(commerceService.getCommerceById(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    @Operation(
            summary = "Crear un nuevo comercio",
            description = "Crea un nuevo comercio con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommerceRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Comercio",
                                            value = "{\"identificationCard\": 35410728, \"enabled\": true, \"hasName\": \"abcde##\", \"contactClientName\": \"Pepe POS\"}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<CommerceResponseDTO> createCommerce(@RequestBody CommerceRequestDTO commerceDTO) {
        return new ResponseEntity<>(commerceService.createCommerce(commerceDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un comercio",
            description = "Actualiza los datos de un comercio existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReserveRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Actualización de Comercio",
                                            value = "{\"identificationCard\": 44125577, \"enabled\": fals, \"hasName\": \"!!mmnnpp\", \"contactClientName\": \"Moni POS\"}"
                                    )
                            }
                    )
            ),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del comercio a actualizar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<CommerceResponseDTO> updateCommerce(@PathVariable Long id, @RequestBody CommerceRequestDTO commerceDTO) {
        return new ResponseEntity<>(commerceService.updateCommerce(id, commerceDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un comercio",
            description = "Elimina un comercio existente por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del comercio a eliminar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<Void> deleteCommerce(@PathVariable Long id) {
        commerceService.deleteCommerce(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
