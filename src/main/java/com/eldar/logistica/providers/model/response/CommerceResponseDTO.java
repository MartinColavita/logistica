package com.eldar.logistica.providers.model.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommerceResponseDTO {
    private Long id;
    private String identificationCard;
    private Boolean enabled;
    private String hashName;
    private String contactClientName;
}
