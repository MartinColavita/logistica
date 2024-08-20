package com.eldar.logistica.providers.services.impl;

import com.eldar.logistica.delivery.domain.entities.DeliveryToEldar;
import com.eldar.logistica.delivery.domain.repositories.DeliveryToEldarRepository;
import com.eldar.logistica.providers.domain.entities.Provider;
import com.eldar.logistica.providers.domain.entities.PurchaseOrder;
import com.eldar.logistica.providers.domain.repositories.ProviderRepository;
import com.eldar.logistica.providers.domain.repositories.PurchaseOrderRepository;
import com.eldar.logistica.providers.model.request.PurchaseOrderRequestDTO;
import com.eldar.logistica.providers.model.response.PurchaseOrderResponseDTO;
import com.eldar.logistica.providers.services.contracts.PurchaseOrderService;
import com.eldar.logistica.providers.utils.mappers.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProviderRepository providerRepository;
    private final DeliveryToEldarRepository deliveryToEldarRepository;



    @Override
    public List<PurchaseOrderResponseDTO> getAllPurchaseOrders() {
        try {
            return purchaseOrderRepository.findAll()
                    .stream()
                    .map(Mapper::toPurchaseOrderResponseDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch purchase orders", e);
        }
    }


    @Override
    public PurchaseOrderResponseDTO getPurchaseOrderById(Long id) {
        try {
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));

            return Mapper.toPurchaseOrderResponseDTO(purchaseOrder);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch purchase order by id", e);
        }
    }


    @Override
    public PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderDTO) {
        try {
            Provider provider = providerRepository.findById(purchaseOrderDTO.getProviderId())
                    .orElseThrow(() -> new EntityNotFoundException("Provider not found"));

            DeliveryToEldar deliveryToEldar = deliveryToEldarRepository.findById(purchaseOrderDTO.getDeliveryToEldarId())
                    .orElseThrow(() -> new EntityNotFoundException("Delivery to Eldar not found"));

            PurchaseOrder purchaseOrder = Mapper.toPurchaseOrderEntity(purchaseOrderDTO, provider,deliveryToEldar);
            PurchaseOrder savedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
            return Mapper.toPurchaseOrderResponseDTO(savedPurchaseOrder);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create purchase order", e);
        }
    }


    @Override
    public PurchaseOrderResponseDTO updatePurchaseOrder(Long id, PurchaseOrderRequestDTO purchaseOrderDTO) {
        try {
            PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));

            Provider provider = providerRepository.findById(purchaseOrderDTO.getProviderId())
                    .orElseThrow(() -> new EntityNotFoundException("Provider not found"));

            DeliveryToEldar deliveryToEldar = deliveryToEldarRepository.findById(purchaseOrderDTO.getDeliveryToEldarId())
                    .orElseThrow(() -> new EntityNotFoundException("Delivery to Eldar not found"));

            purchaseOrder.setProvider(provider);
            purchaseOrder.setDeliveryToEldar(deliveryToEldar);
            purchaseOrder.setEstimatedTime(purchaseOrderDTO.getEstimatedTime());
            purchaseOrder.setStatus(purchaseOrderDTO.getStatus());
            purchaseOrder.setPurchaseDate(purchaseOrderDTO.getPurchaseDate());

            PurchaseOrder updatedPurchaseOrder = purchaseOrderRepository.save(purchaseOrder);
            return Mapper.toPurchaseOrderResponseDTO(updatedPurchaseOrder);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update purchase order", e);
        }
    }



    @Override
    public void deletePurchaseOrder(Long id) {
        try {
            purchaseOrderRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("PurchaseOrder not found"));

            purchaseOrderRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to delete purchase order", e);
        }
    }


}
