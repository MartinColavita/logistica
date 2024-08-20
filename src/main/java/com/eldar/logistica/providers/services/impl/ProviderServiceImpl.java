package com.eldar.logistica.providers.services.impl;

import com.eldar.logistica.providers.domain.entities.Provider;
import com.eldar.logistica.providers.domain.repositories.ProviderRepository;
import com.eldar.logistica.providers.model.request.ProviderRequestDTO;
import com.eldar.logistica.providers.model.response.ProviderResponseDTO;
import com.eldar.logistica.providers.services.contracts.ProviderService;
import com.eldar.logistica.providers.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class ProviderServiceImpl implements ProviderService {

    private final ProviderRepository providerRepository;



    @Override
    public List<ProviderResponseDTO> getAllProviders() {
        try {
            return providerRepository.findAll()
                    .stream()
                    .map(Mapper::toProviderResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all providers", e);
        }
    }


    @Override
    public ProviderResponseDTO getProviderById(Long id) {
        try {
            Provider provider = providerRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
            return Mapper.toProviderResponseDTO(provider);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching provider by id", e);
        }
    }


    @Override
    public ProviderResponseDTO createProvider(ProviderRequestDTO providerDTO) {
        try {
            Provider provider = Mapper.toProviderEntity(providerDTO);
            Provider savedProvider = providerRepository.save(provider);
            return Mapper.toProviderResponseDTO(savedProvider);
        } catch (Exception e) {
            throw new RuntimeException("Error creating provider", e);
        }
    }


    @Override
    public ProviderResponseDTO updateProvider(Long id, ProviderRequestDTO providerDTO) {
        try {
            Provider provider = providerRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Provider not found"));

            provider.setName(providerDTO.getName());
            provider.setContactVendorName(providerDTO.getContactVendorName());

            Provider updatedProvider = providerRepository.save(provider);
            return Mapper.toProviderResponseDTO(updatedProvider);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating provider", e);
        }
    }


    @Override
    public void deleteProvider(Long id) {
        try {
            providerRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting provider", e);
        }
    }

}

