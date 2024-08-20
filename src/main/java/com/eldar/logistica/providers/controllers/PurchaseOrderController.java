package com.eldar.logistica.providers.controllers;

import com.eldar.logistica.providers.model.request.CommerceRequestDTO;
import com.eldar.logistica.providers.model.request.PurchaseOrderRequestDTO;
import com.eldar.logistica.providers.model.request.ReserveRequestDTO;
import com.eldar.logistica.providers.model.response.PurchaseOrderResponseDTO;
import com.eldar.logistica.providers.services.contracts.PurchaseOrderService;
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
@Tag(name = "PurchaseOrders", description = "API para administrar ordenes de compra")
@RestController
@RequestMapping("/api/purchaseOrders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;


    @GetMapping("/all")
    @Operation(
            summary = "Obtener todas las ordenes de compra",
            description = "Devuelve una lista de todas las ordenes de compra disponibles."
    )
    public ResponseEntity<List<PurchaseOrderResponseDTO>> getAllPurchaseOrders() {
        return new ResponseEntity<>(purchaseOrderService.getAllPurchaseOrders(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener una orden de compra por ID",
            description = "Devuelve una orden de compra según el ID proporcionado.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la orden de compra",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<PurchaseOrderResponseDTO> getPurchaseOrderById(@PathVariable Long id) {
        return new ResponseEntity<>(purchaseOrderService.getPurchaseOrderById(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    @Operation(
            summary = "Crear una nueva orden de compra",
            description = "Crea una nueva orden de compra con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommerceRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de orden de compra",
                                            value = "{\"providerId\": 1, \"deliveryToEldarId\": 1, \"estimatedTime\": \"2024-08-21\", \"status\": \"comprado\", \"purchaseDate\": \"2024-08-21\"}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<PurchaseOrderResponseDTO> createPurchaseOrder(@RequestBody PurchaseOrderRequestDTO purchaseOrderDTO) {
        return new ResponseEntity<>(purchaseOrderService.createPurchaseOrder(purchaseOrderDTO), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Actualizar una orden de compra",
            description = "Actualiza los datos de una orden de compra existente.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ReserveRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de actualización de orden de compra",
                                            value = "{\"providerId\": 1, \"deliveryToEldarId\": 1, \"estimatedTime\": \"2024-08-22\", \"status\": \"recibido\", \"purchaseDate\": \"2024-08-21\"}"
                                    )
                            }
                    )
            ),
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la orden de compra a actualizar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<PurchaseOrderResponseDTO> updatePurchaseOrder(@PathVariable Long id, @RequestBody PurchaseOrderRequestDTO purchaseOrderDTO) {
        return new ResponseEntity<>(purchaseOrderService.updatePurchaseOrder(id, purchaseOrderDTO), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Eliminar una orden de compra",
            description = "Elimina una orden de compra existente por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la orden de compra a eliminar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable Long id) {
        purchaseOrderService.deletePurchaseOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

