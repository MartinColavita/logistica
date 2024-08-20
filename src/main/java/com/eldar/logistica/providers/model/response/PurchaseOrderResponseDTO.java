package com.eldar.logistica.providers.model.response;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;


@Builder
@Data
public class PurchaseOrderResponseDTO {
    private Long id;
    private Long providerId;
    private Long deliveryToEldarId;
    private Date estimatedTime;
    private String status;
    private Date purchaseDate;
}
