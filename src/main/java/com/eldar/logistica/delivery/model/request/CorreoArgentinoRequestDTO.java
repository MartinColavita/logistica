package com.eldar.logistica.delivery.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class CorreoArgentinoRequestDTO {
    private Long id;
    private String status;
    private String trackingId;
}
