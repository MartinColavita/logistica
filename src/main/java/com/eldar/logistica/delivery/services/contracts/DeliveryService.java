package com.eldar.logistica.delivery.services.contracts;


import com.eldar.logistica.delivery.model.request.DeliveryRequestDTO;
import com.eldar.logistica.delivery.model.response.AndreaniResponseDTO;
import com.eldar.logistica.delivery.model.response.CorreoArgentinoResponseDTO;
import com.eldar.logistica.delivery.model.response.DeliveryResponseDTO;
import com.eldar.logistica.delivery.model.response.OcaResponseDTO;

import java.util.List;


public interface DeliveryService {
    List<DeliveryResponseDTO> getAllDeliveries();
    DeliveryResponseDTO getDeliveryById(Long id);
    DeliveryResponseDTO createDelivery(DeliveryRequestDTO deliveryRequestDTO);
    DeliveryResponseDTO updateDelivery(Long id, DeliveryRequestDTO deliveryRequestDTO);
    void deleteDelivery(Long id);

    /** Métodos específicos de Andreani */
    AndreaniResponseDTO getAndreaniStatus(Long id);

    /** Métodos específicos de OCA */
    OcaResponseDTO getOcaStatus(Long id);

    /** Métodos específicos de Correo Argentino */
    CorreoArgentinoResponseDTO getCorreoArgentinoStatus(Long id);

}
