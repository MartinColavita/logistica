package com.eldar.logistica.delivery.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class AndreaniResponseDTO {
    private Long id;
    private String status;
    private String message;
    private String trackingId;
}
