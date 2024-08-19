package com.eldar.logistica.terminals.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Data
public class TerminalModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    // TODO -> relacionar idOC con la tabla de ordenes de compra, ya que este es el id de la orden de compra
    private Long idOC;
    private Long model;
    private String serie;


    /** Una terminal va a tener múltiples estados (a lo largo del tiempo).
     * tiene una relación de uno a muchos (una terminal tiene muchos estados) */
    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TerminalState> terminalStates;

}
