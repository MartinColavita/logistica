package com.eldar.logistica.terminals.model.response;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;


@Builder
@Data
public class TerminalStateResponseDTO {
    private Long id;
    private String status;
    private Date date;
    private Long idTerminal;
}
