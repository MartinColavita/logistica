package com.eldar.logistica.providers.model.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Builder
@Data
public class PurchaseOrderResponseDTO {
    private Long id;
    private Long providerId;
    private Long deliveryToEldarId;
    private String estimatedTime;
    private String status;
    private LocalDate purchaseDate;
}
