package com.eldar.logistica.delivery.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor @NoArgsConstructor
public class OcaResponseDTO {
    private Long id;
    private String status;
}
