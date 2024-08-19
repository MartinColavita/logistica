package com.eldar.logistica.terminals.controllers;

import com.eldar.logistica.terminals.model.request.TerminalModelsRequestDTO;
import com.eldar.logistica.terminals.model.request.TerminalStateRequestDTO;
import com.eldar.logistica.terminals.model.response.TerminalModelsResponseDTO;
import com.eldar.logistica.terminals.model.response.TerminalStateResponseDTO;
import com.eldar.logistica.terminals.services.contracts.TerminalsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@AllArgsConstructor
@RestController
@Tag(name ="Terminal", description = "API para administrar terminales")
@RequestMapping("/api/terminals")
public class TerminalController {

    private final TerminalsService terminalsService;



    @GetMapping("/estates")
    @Operation(
            summary = "Obtener terminales por estado",
            description = "Devuelve una lista de terminales según el estado proporcionado (purchased, commited y recived).",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "status",
                            description = "Estado de la terminal",
                            required = true,
                            example = "purchased"
                    )
            }
    )
    public List<TerminalModelsResponseDTO>getTerminalsByStatus(@RequestParam String status) {
        return terminalsService.getTerminalsByStatus(status);
    }


    @GetMapping("/all")
    @Operation(
            summary = "Obtener todas las terminales",
            description = "Devuelve una lista de todas las terminales."
    )
    public List<TerminalModelsResponseDTO>getTerminals() {
        return terminalsService.getTerminals();
    }


    @PostMapping("/create")
    @Operation(
            summary = "Crear una nueva terminal",
            description = "Crea una nueva terminal con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TerminalModelsRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Terminal 1",
                                            value = "{\"brand\": \"Verifone\", \"idOC\": 1, \"model\": 1234, \"serie\": \"SN12345678\"}"
                                    )
                            }
                    )
            )
    )
    public TerminalModelsResponseDTO createTerminal(@RequestBody TerminalModelsRequestDTO terminal) {
        return terminalsService.createTerminal(terminal);
    }


    @PostMapping("/state")
    @Operation(
            summary = "Crear un nuevo estado de terminal",
            description = "Crea un nuevo estado asociado a una terminal existente (purchased, commited y recived).",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TerminalStateRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Estado de Terminal",
                                            value = "{\"idTerminal\": 1, \"status\": \"purchased\", \"date\": \"2024-08-01\"}"
                                    )
                            }
                    )
            )
    )
    public TerminalStateResponseDTO createTerminalState(@RequestBody TerminalStateRequestDTO state) {
        return terminalsService.createTerminalState(state);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una terminal",
            description = "Actualiza los datos de una terminal existente por su ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TerminalModelsRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Actualización de Terminal",
                                            value = "{\"brand\": \"Verifone\", \"idOC\": 1, \"model\": 1234, \"serie\": \"SN12345678\"}"
                                    )
                            }
                    )
            ),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la terminal a actualizar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public TerminalModelsResponseDTO updateTerminal(@PathVariable Long id, @RequestBody TerminalModelsRequestDTO  terminal) {
        return terminalsService.updateTerminal(id, terminal);
    }


    @PutMapping("/state/{id}")
    @Operation(
            summary = "Actualizar el estado de una terminal",
            description = "Actualiza el estado de una terminal existente por su ID. El estado puede ser purchased, commited y recived.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = TerminalStateRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Actualización de Estado de Terminal",
                                            value = "{\"idTerminal\": 1, \"status\": \"commited\", \"date\": \"2024-08-15\"}"
                                    )
                            }
                    )
            ),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del estado de la terminal a actualizar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public TerminalStateResponseDTO updateTerminalState(@PathVariable Long id, @RequestBody TerminalStateRequestDTO  state) {
        return terminalsService.updateTerminalState(id, state);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una terminal",
            description = "Elimina una terminal existente por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la terminal a eliminar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public void deleteTerminal(@PathVariable Long id) {
        terminalsService.deleteTerminal(id);
    }


    @DeleteMapping("/state/{id}")
    @Operation(
            summary = "Eliminar el estado de una terminal",
            description = "Elimina un estado de terminal existente por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del estado de la terminal a eliminar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public void deleteTerminalState(@PathVariable Long id) {
        terminalsService.deleteTerminalState(id);
    }



}
