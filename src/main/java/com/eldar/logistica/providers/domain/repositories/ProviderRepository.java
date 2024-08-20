package com.eldar.logistica.providers.domain.repositories;

import com.eldar.logistica.providers.domain.entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

}
