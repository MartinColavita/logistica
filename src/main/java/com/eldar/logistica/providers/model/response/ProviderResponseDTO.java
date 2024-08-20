package com.eldar.logistica.providers.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProviderResponseDTO {
    private Long id;
    private String name;
    private String contactVendorName;
}
