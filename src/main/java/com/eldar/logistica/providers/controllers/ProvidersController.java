package com.eldar.logistica.providers.controllers;

import com.eldar.logistica.providers.model.request.ProviderRequestDTO;
import com.eldar.logistica.providers.model.response.ProviderResponseDTO;
import com.eldar.logistica.providers.services.contracts.ProviderService;
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

    // TODO -> implementar swagger
    @GetMapping("/all")
    public ResponseEntity<List<ProviderResponseDTO>> getAllProviders() {
        return ResponseEntity.ok(providerService.getAllProviders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProviderResponseDTO> getProviderById(@PathVariable Long id) {
        return ResponseEntity.ok(providerService.getProviderById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<ProviderResponseDTO> createProvider(@RequestBody ProviderRequestDTO providerRequestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(providerService.createProvider(providerRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProviderResponseDTO> updateProvider(@PathVariable Long id, @RequestBody ProviderRequestDTO providerRequestDTO) {
        return ResponseEntity.ok(providerService.updateProvider(id, providerRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
        return ResponseEntity.noContent().build();
    }

}


