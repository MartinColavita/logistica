package com.eldar.logistica.providers.domain.repositories;

import com.eldar.logistica.providers.domain.entities.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ReserveRepository extends JpaRepository<Reserve, Long> {

}
