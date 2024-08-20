package com.eldar.logistica.providers.utils.mappers;

import com.eldar.logistica.providers.domain.entities.Provider;
import com.eldar.logistica.providers.model.request.ProviderRequestDTO;
import com.eldar.logistica.providers.model.response.ProviderResponseDTO;

public class MapperProviders {

    public static ProviderResponseDTO toProviderDTO(Provider provider) {
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



}
