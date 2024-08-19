package com.eldar.logistica.terminals.domain.repositories;


import com.eldar.logistica.terminals.domain.entities.TerminalState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface TerminalStateRepository extends JpaRepository<TerminalState, Long> {
    List<TerminalState> findByStatus(String status);

}
