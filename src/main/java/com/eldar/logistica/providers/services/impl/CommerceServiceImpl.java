package com.eldar.logistica.providers.services.impl;

import com.eldar.logistica.providers.domain.entities.Commerce;
import com.eldar.logistica.providers.domain.repositories.CommerceRepository;
import com.eldar.logistica.providers.model.request.CommerceRequestDTO;
import com.eldar.logistica.providers.model.response.CommerceResponseDTO;
import com.eldar.logistica.providers.services.contracts.CommerceService;
import com.eldar.logistica.providers.utils.mappers.MapperProviders;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommerceServiceImpl implements CommerceService {

    private final CommerceRepository commerceRepository;



    @Override
    public List<CommerceResponseDTO> getAllCommerces() {
        try {
            return commerceRepository.findAll()
                    .stream()
                    .map(MapperProviders::toCommerceResponseDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch commerces", e);
        }
    }

    @Override
    public CommerceResponseDTO getCommerceById(Long id) {
        try {
            Commerce commerce = commerceRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Commerce not found"));

            return MapperProviders.toCommerceResponseDTO(commerce);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch commerce by id", e);
        }
    }


    @Override
    public CommerceResponseDTO createCommerce(CommerceRequestDTO commerceDTO) {
        try {
            Commerce commerce = MapperProviders.toCommerceEntity(commerceDTO);
            Commerce savedCommerce = commerceRepository.save(commerce);
            return MapperProviders.toCommerceResponseDTO(savedCommerce);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create commerce", e);
        }
    }


    @Override
    public CommerceResponseDTO updateCommerce(Long id, CommerceRequestDTO commerceDTO) {
        try {
            Commerce commerce = commerceRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Commerce not found"));

            commerce.setIdentificationCard(commerceDTO.getIdentificationCard());
            commerce.setEnabled(commerceDTO.getEnabled());
            commerce.setHasName(commerceDTO.getHasName());
            commerce.setContactClientName(commerceDTO.getContactClientName());

            Commerce updatedCommerce = commerceRepository.save(commerce);
            return MapperProviders.toCommerceResponseDTO(updatedCommerce);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update commerce", e);
        }
    }


    @Override
    public void deleteCommerce(Long id) {
        try {
            commerceRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Commerce not found"));

            commerceRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to delete commerce", e);
        }
    }


}
