package com.eldar.logistica.providers.controllers;

import com.eldar.logistica.providers.model.request.ProviderRequestDTO;
import com.eldar.logistica.providers.model.response.ProviderResponseDTO;
import com.eldar.logistica.providers.services.contracts.ProviderService;
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
@Tag(name = "Providers", description = "API para administrar proveedores")
@RestController
@RequestMapping("/api/providers")
public class ProvidersController {

    private final ProviderService providerService;



    @GetMapping("/all")
    @Operation(
            summary = "Obtener todos los proveedores",
            description = "Devuelve una lista de todos los proveedores disponibles."
    )
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders() {
        return ResponseEntity.ok(providerService.getAllProviders());
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener un proveedor por ID",
            description = "Devuelve un proveedor según el ID proporcionado.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del proveedor",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<ProviderResponseDTO> getProviderById(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }


    @PostMapping("/create")
    @Operation(
            summary = "Crear un nuevo proveedor",
            description = "Crea un nuevo proveedor con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProviderRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Proveedor",
                                            value = "{\"name\": \"Proveedor Pos 1\", \"contactVendorName\": \"POSNET\"}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<ProviderResponseDTO> createProvider(@RequestBody ProviderRequestDTO providerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(providerService.createProvider(providerRequestDTO));
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar un proveedor",
            description = "Actualiza los datos de un proveedor existente por su ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProviderRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Actualización de Proveedor",
                                            value = "{\"name\": \"Proveedor Pos 2\", \"contactVendorName\": \"POSMANIA\"}"
                                    )
                            }
                    )
            ),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del proveedor a actualizar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<ProviderResponseDTO> updateProvider(@PathVariable Long id, @RequestBody ProviderRequestDTO providerRequestDTO) {
        return ResponseEntity.ok(providerService.updateProvider(id, providerRequestDTO));
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar un proveedor",
            description = "Elimina un proveedor existente por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del proveedor a eliminar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }


}