package com.eldar.logistica.providers.model.request;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommerceRequestDTO {
    private String identificationCard;
    private Boolean enabled;
    private String hasName;
    private String contactClientName;
}
