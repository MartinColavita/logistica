package com.eldar.logistica.delivery.services.impl;

import com.eldar.logistica.delivery.domain.entities.Delivery;
import com.eldar.logistica.delivery.domain.repositories.DeliveryRepository;
import com.eldar.logistica.delivery.model.request.DeliveryRequestDTO;
import com.eldar.logistica.delivery.model.response.AndreaniResponseDTO;
import com.eldar.logistica.delivery.model.response.CorreoArgentinoResponseDTO;
import com.eldar.logistica.delivery.model.response.DeliveryResponseDTO;
import com.eldar.logistica.delivery.model.response.OcaResponseDTO;
import com.eldar.logistica.delivery.services.contracts.DeliveryService;
import com.eldar.logistica.delivery.utils.mappers.MapperDelivery;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final WebClient.Builder webClientBuilder;
    private final DeliveryRepository deliveryRepository;



    @Override
    public List<DeliveryResponseDTO> getAllDeliveries() {
        try {
            return deliveryRepository.findAll()
                    .stream()
                    .map(MapperDelivery::toDeliveryResponseDTO)
                    .collect(Collectors.toList());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch deliveries", e);
        }
    }


    @Override
    public DeliveryResponseDTO getDeliveryById(Long id) {
        try {
            Delivery delivery = deliveryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Delivery not found with ID: " + id));

            return MapperDelivery.toDeliveryResponseDTO(delivery);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch delivery by id", e);
        }
    }


    @Override
    public DeliveryResponseDTO createDelivery(DeliveryRequestDTO deliveryRequestDTO) {
        try {
            Delivery delivery = MapperDelivery.toDeliveryEntity(deliveryRequestDTO);
            Delivery savedDelivery = deliveryRepository.save(delivery);
            return MapperDelivery.toDeliveryResponseDTO(savedDelivery);
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create delivery", e);
        }
    }


    @Override
    public DeliveryResponseDTO updateDelivery(Long id, DeliveryRequestDTO deliveryRequestDTO) {
        try {
            Delivery delivery = deliveryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Delivery not found with ID: " + id));

            delivery.setName(deliveryRequestDTO.getName());
            delivery.setContactDeliveryname(deliveryRequestDTO.getContactDeliveryName());

            Delivery updatedDelivery = deliveryRepository.save(delivery);
            return MapperDelivery.toDeliveryResponseDTO(updatedDelivery);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update delivery with ID: " + id, e);
        }
    }


    @Override
    public void deleteDelivery(Long id) {
        try {
            deliveryRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Delivery not found with ID: " + id));

            deliveryRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw e;
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to delete delivery with ID: " + id, e);
        }
    }


    /** Metodo Generico que se encarga de llamar a un servicio de terceros */
    private <T> T callApi(String endpoint, Class<T> responseType) {
        try {
            WebClient webClient = webClientBuilder.build();
            return webClient.get()
                    .uri(endpoint)
                    .retrieve()
                    .bodyToMono(responseType)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Failed to call third-party API at endpoint: " + endpoint, e);
        }
    }


    /** Métodos específicos de Andreani*/
    @Override
    public AndreaniResponseDTO getAndreaniStatus(Long id) {
        try {
            String endpoint = "https://api.andreani.com/status/" + id;
            return callApi(endpoint, AndreaniResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Andreani status for ID: " + id, e);
        }
    }

    /** Métodos específicos de OCA */
    @Override
    public OcaResponseDTO getOcaStatus(Long id) {
        try {
            String endpoint = "https://api.oca.com/status/" + id;
            return callApi(endpoint, OcaResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch OCA status for ID: " + id, e);
        }
    }

    /** Métodos específicos de Correo Argentino */
    @Override
    public CorreoArgentinoResponseDTO getCorreoArgentinoStatus(Long id) {
        try {
            String endpoint = "https://api.correoargentino.com/status/" + id;
            return callApi(endpoint, CorreoArgentinoResponseDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Correo Argentino status for ID: " + id, e);
        }
    }




}
