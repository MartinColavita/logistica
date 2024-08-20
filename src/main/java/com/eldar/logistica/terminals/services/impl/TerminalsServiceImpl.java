package com.eldar.logistica.terminals.services.impl;

import com.eldar.logistica.providers.domain.entities.PurchaseOrder;
import com.eldar.logistica.providers.domain.repositories.PurchaseOrderRepository;
import com.eldar.logistica.terminals.domain.entities.TerminalModels;
import com.eldar.logistica.terminals.domain.entities.TerminalState;
import com.eldar.logistica.terminals.domain.repositories.TerminalModelsRepository;
import com.eldar.logistica.terminals.domain.repositories.TerminalStateRepository;
import com.eldar.logistica.terminals.model.request.TerminalModelsRequestDTO;
import com.eldar.logistica.terminals.model.request.TerminalStateRequestDTO;
import com.eldar.logistica.terminals.model.response.TerminalModelsResponseDTO;
import com.eldar.logistica.terminals.model.response.TerminalStateResponseDTO;
import com.eldar.logistica.terminals.services.contracts.TerminalsService;
import com.eldar.logistica.terminals.utils.mappers.MapperTerminals;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class TerminalsServiceImpl implements TerminalsService {

    private final TerminalModelsRepository terminalModelsRepository;
    private final TerminalStateRepository terminalStateRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;



    @Override
    public List<TerminalModelsResponseDTO>getTerminalsByStatus(String status) {
        try {
            return terminalStateRepository.findByStatus(status)
                    .stream()
                    .map(TerminalState::getTerminal)
                    .distinct()
                    .map(MapperTerminals::toResponseDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching terminals by status", e);
        }
    }


    @Override
    public  List<TerminalModelsResponseDTO>getTerminals() {
        try {
            return terminalModelsRepository.findAll()
                    .stream()
                    .map(MapperTerminals::toResponseDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("Error fetching terminals", e);
        }
    }


    @Override
    public TerminalModelsResponseDTO createTerminal(TerminalModelsRequestDTO terminalDTO) {
        try {
            // Obtener la instancia de PurchaseOrder usando el ID proporcionado en el DTO
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(terminalDTO.getIdOC())
                    .orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));

            // Llamar al mapper y pasar la instancia de PurchaseOrder
            TerminalModels terminal = MapperTerminals.toEntity(terminalDTO, purchaseOrder);

            TerminalModels savedTerminal = terminalModelsRepository.save(terminal);

            return MapperTerminals.toResponseDTO(savedTerminal);
        } catch (Exception e) {
            throw new RuntimeException("Error creating terminal", e);
        }
    }

    @Override
    public TerminalStateResponseDTO createTerminalState(TerminalStateRequestDTO stateDTO) {
        try {
            TerminalModels terminal = terminalModelsRepository.findById(stateDTO.getIdTerminal())
                    .orElseThrow(() -> new EntityNotFoundException("Terminal not found"));

            TerminalState state = MapperTerminals.toEntity(stateDTO, terminal);
            TerminalState savedState = terminalStateRepository.save(state);
            return MapperTerminals.toResponseDTO(savedState);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error creating terminal state", e);
        }
    }

    @Override
    public TerminalModelsResponseDTO updateTerminal(Long id, TerminalModelsRequestDTO terminalDTO) {
        try {
            TerminalModels terminal = terminalModelsRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Terminal not found"));

            // Obtener la instancia de PurchaseOrder usando el ID proporcionado en el DTO
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(terminalDTO.getIdOC())
                    .orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));

            terminal.setBrand(terminalDTO.getBrand());
            terminal.setPurchaseOrder(purchaseOrder); // Asignar instancia de PurchaseOrder
            terminal.setModel(terminalDTO.getModel());
            terminal.setSerie(terminalDTO.getSerie());

            TerminalModels updatedTerminal = terminalModelsRepository.save(terminal);

            return MapperTerminals.toResponseDTO(updatedTerminal);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating terminal", e);
        }
    }

    @Override
    public TerminalStateResponseDTO updateTerminalState(Long id, TerminalStateRequestDTO stateDTO) {
        try {
            TerminalState state = terminalStateRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Terminal state not found"));

            TerminalModels terminal = terminalModelsRepository.findById(stateDTO.getIdTerminal())
                    .orElseThrow(() -> new EntityNotFoundException("Terminal not found"));

            state.setStatus(stateDTO.getStatus());
            state.setDate(stateDTO.getDate());
            state.setTerminal(terminal);

            TerminalState updatedState = terminalStateRepository.save(state);
            return MapperTerminals.toResponseDTO(updatedState);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating terminal state", e);
        }
    }

    @Override
    public void deleteTerminal(Long id) {
        try {
            terminalModelsRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting terminal", e);
        }
    }

    @Override
    public void deleteTerminalState(Long id) {
        try {
            terminalStateRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting terminal state", e);
        }
    }


}


