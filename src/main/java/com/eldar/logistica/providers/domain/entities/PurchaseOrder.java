package com.eldar.logistica.providers.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;


@Entity
@Data
@Table(name = "PurchaseOrders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Muchas órdenes de compra (PurchaseOrder) están asociadas a un único proveedor  */
    @ManyToOne
    @JoinColumn(name = "providerId", nullable = false)
    private Provider provider;

    //TODO -> relacionar esto con la tabla "Delivery to Eldar"
    @Column(nullable = false)
    private Long deliveryToEldarId;

    @Column(nullable = false)
    private Date estimatedTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date purchaseDate;
}
