package com.eldar.logistica.delivery.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
public class CorreoArgentinoResponseDTO {
    private Long id;
    private String status;
    private String trackingId;
    private String trackingUrl;
}
