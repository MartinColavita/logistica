package com.eldar.logistica.providers.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Commerces")
public class Commerce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String identificationCard;

    @Column(nullable = false)
    private Boolean enabled;

    @Column(nullable = false)
    private String hasName;

    @Column(nullable = false)
    private String contactClientName;


}
