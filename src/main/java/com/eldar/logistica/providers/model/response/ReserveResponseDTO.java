package com.eldar.logistica.providers.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ReserveResponseDTO {
    private Long id;
    private String status;
    private Long commerceId;
    private Long quantity;
    private Long productId;
    private String brand;
    private Long model;
}
