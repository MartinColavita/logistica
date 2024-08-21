package com.eldar.logistica.delivery.model.request;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class DeliveryRequestDTO {
    private String name;
    private String contactDeliveryName;
    private String trackingId;
}
