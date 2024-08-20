package com.eldar.logistica.providers.services.impl;

import com.eldar.logistica.providers.domain.entities.Commerce;
import com.eldar.logistica.providers.domain.entities.Reserve;
import com.eldar.logistica.providers.domain.repositories.CommerceRepository;
import com.eldar.logistica.providers.domain.repositories.ReserveRepository;
import com.eldar.logistica.providers.model.request.ReserveRequestDTO;
import com.eldar.logistica.providers.model.response.ReserveResponseDTO;
import com.eldar.logistica.providers.services.contracts.ReserveService;
import com.eldar.logistica.providers.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ReserveServiceImpl implements ReserveService {

    private final ReserveRepository reserveRepository;
    private final CommerceRepository commerceRepository;



    @Override
    public List<ReserveResponseDTO> getAllReserves() {
        try {
            return reserveRepository.findAll()
                    .stream()
                    .map(Mapper::toReserveResponseDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all reserves", e);
        }
    }


    @Override
    public ReserveResponseDTO getReserveById(Long id) {
        try {
            Reserve reserve = reserveRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Reserve not found"));

            return Mapper.toReserveResponseDTO(reserve);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching reserve by id", e);
        }
    }


    @Override
    public ReserveResponseDTO createReserve(ReserveRequestDTO reserveDTO) {
        try {
            Commerce commerce = commerceRepository.findById(reserveDTO.getCommerceId())
                    .orElseThrow(() -> new EntityNotFoundException("Commerce not found"));

            Reserve reserve = Mapper.toReserveEntity(reserveDTO, commerce);
            Reserve savedReserve = reserveRepository.save(reserve);
            return Mapper.toReserveResponseDTO(savedReserve);
        } catch (Exception e) {
            throw new RuntimeException("Error creating reserve", e);
        }
    }


    @Override
    public ReserveResponseDTO updateReserve(Long id, ReserveRequestDTO reserveDTO) {
        try {
            Reserve reserve = reserveRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Reserve not found"));

            Commerce commerce = commerceRepository.findById(reserveDTO.getCommerceId())
                    .orElseThrow(() -> new EntityNotFoundException("Commerce not found"));

            reserve.setStatus(reserveDTO.getStatus());
            reserve.setCommerce(commerce);
            reserve.setQuantity(reserveDTO.getQuantity());
            reserve.setBrand(reserveDTO.getBrand());
            reserve.setModel(reserveDTO.getModel());

            Reserve updatedReserve = reserveRepository.save(reserve);
            return Mapper.toReserveResponseDTO(updatedReserve);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating reserve", e);
        }
    }


    @Override
    public void deleteReserve(Long id) {
        try {
            reserveRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting reserve", e);
        }
    }

}
