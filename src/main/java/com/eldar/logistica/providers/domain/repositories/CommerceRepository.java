package com.eldar.logistica.providers.domain.repositories;

import com.eldar.logistica.providers.domain.entities.Commerce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CommerceRepository extends JpaRepository<Commerce, Long> {

}
