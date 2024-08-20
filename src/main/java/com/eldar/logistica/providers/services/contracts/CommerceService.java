package com.eldar.logistica.providers.services.contracts;

import com.eldar.logistica.providers.model.request.CommerceRequestDTO;
import com.eldar.logistica.providers.model.response.CommerceResponseDTO;

import java.util.List;

public interface CommerceService {
    List<CommerceResponseDTO> getAllCommerces();
    CommerceResponseDTO getCommerceById(Long id);
    CommerceResponseDTO createCommerce(CommerceRequestDTO commerceDTO);
    CommerceResponseDTO updateCommerce(Long id, CommerceRequestDTO commerceDTO);
    void deleteCommerce(Long id);
}

