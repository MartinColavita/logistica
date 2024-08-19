package com.eldar.logistica.terminals.services.contracts;


import com.eldar.logistica.terminals.domain.entities.TerminalModels;
import com.eldar.logistica.terminals.domain.entities.TerminalState;
import com.eldar.logistica.terminals.model.request.TerminalModelsRequestDTO;
import com.eldar.logistica.terminals.model.request.TerminalStateRequestDTO;
import com.eldar.logistica.terminals.model.response.TerminalModelsResponseDTO;
import com.eldar.logistica.terminals.model.response.TerminalStateResponseDTO;

import java.util.List;


public interface TerminalsService {
    public List<TerminalModelsResponseDTO> getTerminalsByStatus(String status);
    public List<TerminalModelsResponseDTO> getTerminals();
    TerminalModelsResponseDTO createTerminal(TerminalModelsRequestDTO  terminal);
    TerminalStateResponseDTO createTerminalState(TerminalStateRequestDTO state);
    TerminalModelsResponseDTO updateTerminal(Long id, TerminalModelsRequestDTO terminal);
    TerminalStateResponseDTO updateTerminalState(Long id, TerminalStateRequestDTO state);
    void deleteTerminal(Long id);
    void deleteTerminalState(Long id);

}
