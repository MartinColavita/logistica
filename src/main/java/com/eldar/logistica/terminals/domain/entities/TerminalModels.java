package com.eldar.logistica.terminals.domain.entities;

import com.eldar.logistica.providers.domain.entities.PurchaseOrder;
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
@Table(name = "TerminalModels")
public class TerminalModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;

    /** Muchos terminales (TerminalModels) están asociados a una única orden de compra (PurchaseOrder) */
    @ManyToOne
    @JoinColumn(name = "purchaseOrderId", nullable = false)
    private PurchaseOrder purchaseOrder;

    private Long model;

    private String serie;


    /** Una terminal va a tener múltiples estados (a lo largo del tiempo).
     * tiene una relación de uno a muchos (una terminal tiene muchos estados) */
    @OneToMany(mappedBy = "terminal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TerminalState> terminalStates;

}
