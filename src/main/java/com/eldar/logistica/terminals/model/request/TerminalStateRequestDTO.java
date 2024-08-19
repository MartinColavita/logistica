package com.eldar.logistica.terminals.model.request;

import lombok.Builder;
import lombok.Data;
import java.sql.Date;


@Builder
@Data
public class TerminalStateRequestDTO {
    private Long idTerminal;
    private String status;
    private Date date;
}
