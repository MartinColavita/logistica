package com.eldar.logistica.providers.model.request;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;


@Builder
@Data
public class PurchaseOrderRequestDTO {
    private Long providerId;

    // TODO -> asociar esto con el id de deliveryToEldar
    private Long deliveryToEldarId;

    private Date estimatedTime;
    private String status;
    private Date purchaseDate;

}
