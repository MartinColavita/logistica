package com.eldar.logistica.providers.services.contracts;

import com.eldar.logistica.providers.model.request.ReserveRequestDTO;
import com.eldar.logistica.providers.model.response.ReserveResponseDTO;
import java.util.List;


public interface ReserveService {
    List<ReserveResponseDTO> getAllReserves();
    ReserveResponseDTO getReserveById(Long id);
    ReserveResponseDTO createReserve(ReserveRequestDTO reserveDTO);
    ReserveResponseDTO updateReserve(Long id, ReserveRequestDTO reserveDTO);
    void deleteReserve(Long id);
}
