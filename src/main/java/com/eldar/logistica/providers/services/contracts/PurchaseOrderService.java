package com.eldar.logistica.providers.services.contracts;

import com.eldar.logistica.providers.model.request.PurchaseOrderRequestDTO;
import com.eldar.logistica.providers.model.response.PurchaseOrderResponseDTO;
import java.util.List;


public interface PurchaseOrderService {
    List<PurchaseOrderResponseDTO> getAllPurchaseOrders();
    PurchaseOrderResponseDTO getPurchaseOrderById(Long id);
    PurchaseOrderResponseDTO createPurchaseOrder(PurchaseOrderRequestDTO purchaseOrderDTO);
    PurchaseOrderResponseDTO updatePurchaseOrder(Long id, PurchaseOrderRequestDTO purchaseOrderDTO);
    void deletePurchaseOrder(Long id);
}
