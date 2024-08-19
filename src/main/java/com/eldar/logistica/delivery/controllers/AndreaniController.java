package com.eldar.logistica.delivery.controllers;

import com.eldar.logistica.delivery.domain.entities.Andreani;
import com.eldar.logistica.delivery.model.request.AndreaniRequestDTO;
import com.eldar.logistica.delivery.model.response.AndreaniResponseDTO;
import com.eldar.logistica.delivery.services.contracts.AndreaniService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@Tag(name = "Andreani", description = "API para Ver estado de envios de Andreani")
@RequestMapping("/api/andreani")
public class AndreaniController {

    private final AndreaniService andreaniService;

    @GetMapping("/{id}/order")
    @Operation(
            summary = "Obtener el estado del pedido",
            description = "Recupera el estado de un pedido por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID del pedido",
                            required = true,
                            example = "23451"
                    )
            }
    )
    public AndreaniResponseDTO getOrderStatus(@PathVariable Long id) {
        Andreani andreani = andreaniService.findById(id);
        if (andreani != null) {
            return new AndreaniResponseDTO(andreani.getId(), andreani.getStatus(), "Order status retrieved successfully");
        } else {
            return new AndreaniResponseDTO(id, null, "Order not found");
        }
    }


    @PostMapping("/{id}/status")
    @Operation(
            summary = "Update Order Status",
            description = "Update the status of an order by its ID.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AndreaniRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo 1",
                                            value = "{\"id\": 1, \"status\": \"En preparación\"}"
                                    ),
                                    @ExampleObject(
                                            name = "Ejemplo 2",
                                            value = "{\"id\": 2, \"status\": \"Entregado\"}"
                                    )
                            }
                    )
            )
    )
    public AndreaniResponseDTO updateOrderStatus(@PathVariable Long id, @RequestBody AndreaniRequestDTO request) {
        Andreani updatedAndreani = andreaniService.updateStatus(id, request.getStatus());
        if (updatedAndreani != null) {
            return new AndreaniResponseDTO(updatedAndreani.getId(), updatedAndreani.getStatus(), "Order status updated successfully");
        } else {
            return new AndreaniResponseDTO(id, null, "Order not found");
        }
    }

}