package com.eldar.logistica.providers.controllers;

import com.eldar.logistica.providers.model.request.ReserveRequestDTO;
import com.eldar.logistica.providers.model.response.ReserveResponseDTO;
import com.eldar.logistica.providers.services.contracts.ReserveService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Tag(name = "Reserves", description = "API para administrar reservas")
@RestController
@RequestMapping("/api/reserves")
public class ReserveController {

    private final ReserveService reserveService;


    // TODO -> implementar swagger
    @GetMapping("/all")
    public ResponseEntity<List<ReserveResponseDTO>> getAllReserves() {
        return new ResponseEntity<>(reserveService.getAllReserves(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReserveResponseDTO> getReserveById(@PathVariable Long id) {
        return new ResponseEntity<>(reserveService.getReserveById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ReserveResponseDTO> createReserve(@RequestBody ReserveRequestDTO reserveDTO) {
        return new ResponseEntity<>(reserveService.createReserve(reserveDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReserveResponseDTO> updateReserve(@PathVariable Long id, @RequestBody ReserveRequestDTO reserveDTO) {
        return new ResponseEntity<>(reserveService.updateReserve(id, reserveDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReserve(@PathVariable Long id) {
        reserveService.deleteReserve(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
