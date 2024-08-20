package com.eldar.logistica.terminals.utils.mappers;

import com.eldar.logistica.providers.domain.entities.PurchaseOrder;
import com.eldar.logistica.terminals.domain.entities.*;
import com.eldar.logistica.terminals.model.request.TerminalModelsRequestDTO;
import com.eldar.logistica.terminals.model.request.TerminalStateRequestDTO;
import com.eldar.logistica.terminals.model.response.TerminalModelsResponseDTO;
import com.eldar.logistica.terminals.model.response.TerminalStateResponseDTO;


public class MapperTerminals {

    public static TerminalModelsResponseDTO toResponseDTO(TerminalModels terminal) {
        return TerminalModelsResponseDTO.builder()
                .id(terminal.getId())
                .brand(terminal.getBrand())
                .idOC(terminal.getPurchaseOrder().getId())
                .model(terminal.getModel())
                .serie(terminal.getSerie())
                .build();
    }

    public static TerminalModels toEntity(TerminalModelsRequestDTO dto, PurchaseOrder purchaseOrder) {
        return TerminalModels.builder()
                .brand(dto.getBrand())
                .purchaseOrder(purchaseOrder)
                .model(dto.getModel())
                .serie(dto.getSerie())
                .build();
    }

    public static TerminalStateResponseDTO toResponseDTO(TerminalState state) {
        return TerminalStateResponseDTO.builder()
                .id(state.getId())
                .status(state.getStatus())
                .date(state.getDate())
                .idTerminal(state.getTerminal().getId())
                .build();
    }

    public static TerminalState toEntity(TerminalStateRequestDTO dto, TerminalModels terminal) {
        return TerminalState.builder()
                .status(dto.getStatus())
                .date(dto.getDate())
                .terminal(terminal)
                .build();
    }

}
