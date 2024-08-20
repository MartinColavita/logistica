package com.eldar.logistica.delivery.domain.repositories;

import com.eldar.logistica.delivery.domain.entities.DeliveryToEldar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryToEldarRepository extends JpaRepository<DeliveryToEldar, Long> {

}
