package com.eldar.logistica.delivery.domain.repositories;

import com.eldar.logistica.delivery.domain.entities.Andreani;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AndreaniRepository extends JpaRepository<Andreani, Long> {

}
