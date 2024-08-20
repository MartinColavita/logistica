package com.eldar.logistica.providers.utils.mappers;

import com.eldar.logistica.delivery.domain.entities.DeliveryToEldar;
import com.eldar.logistica.providers.domain.entities.Commerce;
import com.eldar.logistica.providers.domain.entities.Provider;
import com.eldar.logistica.providers.domain.entities.PurchaseOrder;
import com.eldar.logistica.providers.domain.entities.Reserve;
import com.eldar.logistica.providers.model.request.CommerceRequestDTO;
import com.eldar.logistica.providers.model.request.ProviderRequestDTO;
import com.eldar.logistica.providers.model.request.PurchaseOrderRequestDTO;
import com.eldar.logistica.providers.model.request.ReserveRequestDTO;
import com.eldar.logistica.providers.model.response.CommerceResponseDTO;
import com.eldar.logistica.providers.model.response.ProviderResponseDTO;
import com.eldar.logistica.providers.model.response.PurchaseOrderResponseDTO;
import com.eldar.logistica.providers.model.response.ReserveResponseDTO;


public class Mapper {

    public static ProviderResponseDTO toProviderResponseDTO(Provider provider) {
        return ProviderResponseDTO.builder()
                .id(provider.getId())
                .name(provider.getName())
                .contactVendorName(provider.getContactVendorName())
                .build();
    }

    public static Provider toProviderEntity(ProviderRequestDTO providerDTO) {
        return Provider.builder()
                .name(providerDTO.getName())
                .contactVendorName(providerDTO.getContactVendorName())
                .build();
    }


    public static ReserveResponseDTO toReserveResponseDTO(Reserve reserve) {
        return ReserveResponseDTO.builder()
                .id(reserve.getId())
                .status(reserve.getStatus())
                .commerceId(reserve.getCommerce().getId())
                .quantity(reserve.getQuantity())
                .productId(reserve.getProductId())
                .brand(reserve.getBrand())
                .model(reserve.getModel())
                .build();
    }

    public static Reserve toReserveEntity(ReserveRequestDTO dto, Commerce commerce) {
        return Reserve.builder()
                .status(dto.getStatus())
                .commerce(commerce)
                .quantity(dto.getQuantity())
                .brand(dto.getBrand())
                .model(dto.getModel())
                .build();
    }


    public static CommerceResponseDTO toCommerceResponseDTO(Commerce commerce) {
        return CommerceResponseDTO.builder()
                .id(commerce.getId())
                .identificationCard(commerce.getIdentificationCard())
                .enabled(commerce.getEnabled())
                .hashName(commerce.getHasName())
                .contactClientName(commerce.getContactClientName())
                .build();
    }

    public static Commerce toCommerceEntity(CommerceRequestDTO dto) {
        return Commerce.builder()
                .identificationCard(dto.getIdentificationCard())
                .enabled(dto.getEnabled())
                .hasName(dto.getHasName())
                .contactClientName(dto.getContactClientName())
                .build();
    }


    public static PurchaseOrderResponseDTO toPurchaseOrderResponseDTO(PurchaseOrder purchaseOrder) {
        return PurchaseOrderResponseDTO.builder()
                .id(purchaseOrder.getId())
                .providerId(purchaseOrder.getProvider().getId())
                .deliveryToEldarId(purchaseOrder.getDeliveryToEldar().getId())
                .estimatedTime(purchaseOrder.getEstimatedTime())
                .status(purchaseOrder.getStatus())
                .purchaseDate(purchaseOrder.getPurchaseDate())
                .build();
    }

    public static PurchaseOrder toPurchaseOrderEntity(PurchaseOrderRequestDTO dto, Provider provider, DeliveryToEldar deliveryToEldar) {
        return PurchaseOrder.builder()
                .provider(provider)
                .deliveryToEldar(deliveryToEldar)
                .estimatedTime(dto.getEstimatedTime())
                .status(dto.getStatus())
                .purchaseDate(dto.getPurchaseDate())
                .build();
    }

}
