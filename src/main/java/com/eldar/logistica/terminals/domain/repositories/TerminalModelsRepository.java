package com.eldar.logistica.terminals.domain.repositories;

import com.eldar.logistica.terminals.domain.entities.TerminalModels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TerminalModelsRepository extends JpaRepository<TerminalModels, Long> {

}
