package com.eldar.logistica.providers.domain.repositories;

import com.eldar.logistica.providers.domain.entities.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
