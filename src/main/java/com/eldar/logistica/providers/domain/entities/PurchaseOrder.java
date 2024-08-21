package com.eldar.logistica.providers.domain.entities;

import com.eldar.logistica.delivery.domain.entities.Delivery;
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
@Table(name = "PurchaseOrders")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Muchas órdenes de compra (PurchaseOrder) están asociadas a un único proveedor  */
    @ManyToOne
    @JoinColumn(name = "providerId", nullable = false)
    private Provider provider;

    /** Muchas órdenes de compra (PurchaseOrder) están asociadas a un único deliveryToEldar  */
    @ManyToOne
    @JoinColumn(name = "deliveryId", nullable = false)
    private Delivery delivery;

    @Column(nullable = false)
    private Date estimatedTime;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Date purchaseDate;
}
