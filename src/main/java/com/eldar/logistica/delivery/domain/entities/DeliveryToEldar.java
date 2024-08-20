package com.eldar.logistica.delivery.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor @NoArgsConstructor
@Data
@Table(name = "Commerces")
public class DeliveryToEldar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contactDeliveryname;

}
