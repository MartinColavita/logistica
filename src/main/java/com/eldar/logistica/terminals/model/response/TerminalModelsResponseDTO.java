package com.eldar.logistica.terminals.model.response;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class TerminalModelsResponseDTO {
    private Long id;
    private String brand;
    private Long idOC;
    private Long model;
    private String serie;
}
