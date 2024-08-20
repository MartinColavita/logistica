package com.eldar.logistica.providers.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReserveRequestDTO {
    private String status;
    private Long commerceId;
    private Long quantity;
    private String brand;
    private Long model;
}
