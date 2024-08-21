package com.eldar.logistica.delivery.controllers;

import com.eldar.logistica.delivery.model.request.DeliveryRequestDTO;
import com.eldar.logistica.delivery.model.response.AndreaniResponseDTO;
import com.eldar.logistica.delivery.model.response.CorreoArgentinoResponseDTO;
import com.eldar.logistica.delivery.model.response.DeliveryResponseDTO;
import com.eldar.logistica.delivery.model.response.OcaResponseDTO;
import com.eldar.logistica.delivery.services.contracts.DeliveryService;
import com.eldar.logistica.providers.model.request.CommerceRequestDTO;
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
@RestController
@Tag(name = "Delivery", description = "API para gestionar entregas")
@RequestMapping("/api/delivery")
public class DeliveryController {

    private final DeliveryService deliveryService;



    @GetMapping("/all")
    @Operation(
            summary = "Obtener todas las entregas",
            description = "Devuelve una lista de todas las entregas registradas."
    )
    public ResponseEntity<List<DeliveryResponseDTO>> getAllDeliveries() {
        return new ResponseEntity<>(deliveryService.getAllDeliveries(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener una entrega por ID",
            description = "Devuelve una entrega según el ID proporcionado.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la entrega",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<DeliveryResponseDTO> getDeliveryById(@PathVariable Long id) {
        return new ResponseEntity<>(deliveryService.getDeliveryById(id), HttpStatus.OK);
    }


    @PostMapping("/create")
    @Operation(
            summary = "Crear una nueva entrega",
            description = "Crea una nueva entrega con los datos proporcionados.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CommerceRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Actualización de Entrega",
                                            value = "{\"name\": \"Andreani\", \"contactDeliveryName\": \"pepe andreani\"}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<DeliveryResponseDTO> createDelivery(@RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        return new ResponseEntity<>(deliveryService.createDelivery(deliveryRequestDTO), HttpStatus.CREATED);
    }


    @PutMapping("/update/{id}")
    @Operation(
            summary = "Actualizar una entrega existente",
            description = "Actualiza los datos de una entrega existente según el ID proporcionado.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la entrega a actualizar",
                            required = true,
                            example = "1"
                    )
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DeliveryRequestDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Ejemplo de Actualización de Entrega",
                                            value = "{\"name\": \"Walking Man\", \"contactDeliveryName\": \"Forrest Gump\"}"
                                    )
                            }
                    )
            )
    )
    public ResponseEntity<DeliveryResponseDTO> updateDelivery(@PathVariable Long id, @RequestBody DeliveryRequestDTO deliveryRequestDTO) {
        return new ResponseEntity<>(deliveryService.updateDelivery(id, deliveryRequestDTO), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Eliminar una entrega",
            description = "Elimina una entrega según el ID proporcionado.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la entrega a eliminar",
                            required = true,
                            example = "1"
                    )
            }
    )
    public ResponseEntity<Void> deleteDelivery(@PathVariable Long id) {
        deliveryService.deleteDelivery(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /** Métodos específicos de Andreani */
    @GetMapping("/{id}/staus/andreani")
    @Operation(
            summary = "Obtener el estado del pedido de Andreani",
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
    public ResponseEntity<AndreaniResponseDTO> getOrderStatusAndreani(@PathVariable Long id) {
        return new ResponseEntity<>(deliveryService.getAndreaniStatus(id), HttpStatus.OK);
    }


    /** Métodos específicos de OCA */
    @GetMapping("/{id}/status/oca")
    @Operation(
            summary = "Obtener el estado del pedido de entrega",
            description = "Recupera el estado de un pedido por por su ID.",
            parameters = {
                    @io.swagger.v3.oas.annotations.Parameter(
                            name = "id",
                            description = "ID de la entrega",
                            required = true,
                            example = "23451"
                    )
            }
    )
    public ResponseEntity<OcaResponseDTO> getOrderStatusOca(@PathVariable Long id) {
        return new ResponseEntity<>(deliveryService.getOcaStatus(id), HttpStatus.OK);
    }


    /** Métodos específicos de Correo Argentino */
    @GetMapping("/{id}/status/correoargentino")
    @Operation(
            summary = "Obtener el estado del pedido de Correo Argentino",
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
    public ResponseEntity<CorreoArgentinoResponseDTO> getOrderStatusCorreoArg(@PathVariable Long id) {
        return new ResponseEntity<>(deliveryService.getCorreoArgentinoStatus(id), HttpStatus.OK);
    }


}