package com.eldar.logistica.terminals.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Data
@Table(name = "TerminalStates")
public class TerminalState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idTerminal", nullable = false)
    private TerminalModels terminal;

    private String status;
    private Date date;


}
