package com.eldar.logistica.providers.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProviderRequestDTO {
    private String name;
    private String contactVendorName;
}
