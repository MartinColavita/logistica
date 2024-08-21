package com.eldar.logistica.delivery.domain.repositories;

import com.eldar.logistica.delivery.domain.entities.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
