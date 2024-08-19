package com.eldar.logistica.terminals.model.request;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class TerminalModelsRequestDTO {
    private String brand;
    private Long idOC;
    private Long model;
    private String serie;
}
