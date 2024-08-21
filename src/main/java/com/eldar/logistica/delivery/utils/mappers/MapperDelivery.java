package com.eldar.logistica.delivery.utils.mappers;

import com.eldar.logistica.delivery.domain.entities.Delivery;
import com.eldar.logistica.delivery.model.request.DeliveryRequestDTO;
import com.eldar.logistica.delivery.model.response.DeliveryResponseDTO;

public class MapperDelivery {

    public static DeliveryResponseDTO toDeliveryResponseDTO(Delivery delivery) {
        return DeliveryResponseDTO.builder()
                .id(delivery.getId())
                .name(delivery.getName())
                .contactDeliveryName(delivery.getContactDeliveryname())
                .build();
    }

    public static Delivery toDeliveryEntity(DeliveryRequestDTO deliveryRequestDTO) {
        return Delivery.builder()
                .name(deliveryRequestDTO.getName())
                .contactDeliveryname(deliveryRequestDTO.getContactDeliveryName())
                .trackingId(deliveryRequestDTO.getTrackingId())
                .build();
    }


}
