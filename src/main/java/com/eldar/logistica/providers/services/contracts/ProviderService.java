package com.eldar.logistica.providers.services.contracts;

import com.eldar.logistica.providers.model.request.ProviderRequestDTO;
import com.eldar.logistica.providers.model.response.ProviderResponseDTO;
import java.util.List;


public interface ProviderService {
    List<ProviderResponseDTO> getAllProviders();
    ProviderResponseDTO getProviderById(Long id);
    ProviderResponseDTO createProvider(ProviderRequestDTO providerDTO);
    ProviderResponseDTO updateProvider(Long id, ProviderRequestDTO providerDTO);
    void deleteProvider(Long id);
}
