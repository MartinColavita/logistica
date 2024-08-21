package com.eldar.logistica.delivery.model.response;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class DeliveryResponseDTO {
    private Long id;
    private String name;
    private String contactDeliveryName;
    private String trackingId;
}
