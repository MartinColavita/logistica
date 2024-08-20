package com.eldar.logistica.providers.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Reserves")
public class Reserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String status;

    /** Muchas reservas (Reserve) están asociadas a un único comercio  */
    @ManyToOne
    @JoinColumn(name = "commerceId", nullable = false)
    private Commerce commerce;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private Long model;

}
