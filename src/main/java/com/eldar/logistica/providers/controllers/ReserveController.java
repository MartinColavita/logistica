package com.eldar.logistica.providers.controllers;

import com.eldar.logistica.providers.model.request.ReserveRequestDTO;
import com.eldar.logistica.providers.model.response.ReserveResponseDTO;
import com.eldar.logistica.providers.services.contracts.ReserveService;
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
@Tag(name = "Reserves", description = "API para administrar reservas")
@RestController
@RequestMapping("/api/reserves")
public class ReserveController {

    private final ReserveService reserveService;



    @GetMapping("/all")
    @Operation(
            summary = "Obtener todas las reservas",
            description = "Devuelve una lista de todas las reservas disponibles."
    )
    public ResponseEntity<List<ReserveResponseDTO>> getAllReserves() {
        return new ResponseEntity<>(reserveService.getAllReserves(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener una reserva por ID",
            description = "Devuelve una reserva según el ID proporcionado.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la reserva",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<ReserveResponseDTO> getReserveById(@PathVariable Long id) {
        return new ResponseEntity<>(reserveService.getReserveById(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    @Operation(
            summary = "Crear una nueva reserva",
            description = "Crea una nueva reserva con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReserveRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Reserva",
                                            value = "{\"status\": \"Pendiente\", \"commerceId\": 1, \"quantity\": 100, \"brand\": \"new pos\", \"model\": 1234}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<ReserveResponseDTO> createReserve(@RequestBody ReserveRequestDTO reserveDTO) {
        return new ResponseEntity<>(reserveService.createReserve(reserveDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una reserva",
            description = "Actualiza los datos de una reserva existente por su ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReserveRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Actualización de Reserva",
                                            value = "{\"status\": \"Aceptado\", \"commerceId\": 2, \"quantity\": 50, \"brand\": \"old pos\", \"model\": 567}"
                                    )
                            }
                    )
            ),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la reserva a actualizar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<ReserveResponseDTO> updateReserve(@PathVariable Long id, @RequestBody ReserveRequestDTO reserveDTO) {
        return new ResponseEntity<>(reserveService.updateReserve(id, reserveDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una reserva",
            description = "Elimina una reserva existente por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la reserva a eliminar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<Void> deleteReserve(@PathVariable Long id) {
        reserveService.deleteReserve(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}