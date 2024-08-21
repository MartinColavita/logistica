package com.eldar.logistica.services;

import com.eldar.logistica.delivery.model.response.AndreaniResponseDTO;
import com.eldar.logistica.delivery.model.response.CorreoArgentinoResponseDTO;
import com.eldar.logistica.delivery.model.response.OcaResponseDTO;
import com.eldar.logistica.delivery.services.impl.DeliveryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.web.reactive.function.client.WebClient;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Pruebas unitarias para la clase DeliveryServiceImpl.
 */
@ExtendWith(MockitoExtension.class)
public class DeliveryServiceImplTest {

    @Mock
    private WebClient.Builder webClientBuilder;

    @InjectMocks
    private DeliveryServiceImpl deliveryService;

    private WebClient webClient;

    /**
     * Configura el entorno de prueba antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        webClient = mock(WebClient.class, RETURNS_DEEP_STUBS);
        when(webClientBuilder.build()).thenReturn(webClient);
    }

    /**
     * Prueba el método getAndreaniStatus del DeliveryServiceImpl.
     */
    @Test
    public void testGetAndreaniStatus() {
        AndreaniResponseDTO response = new AndreaniResponseDTO(1L, "En camino", "Mensaje", "12345");
        when(webClient.get().uri("https://api.andreani.com/status/1").retrieve().bodyToMono(AndreaniResponseDTO.class).block())
                .thenReturn(response);

        AndreaniResponseDTO result = deliveryService.getAndreaniStatus(1L);
        assertNotNull(result);
        assertEquals("En camino", result.getStatus());
    }


    /**
     * Prueba el método getOcaStatus del DeliveryServiceImpl.
     */
    @Test
    public void testGetOcaStatus() {
        OcaResponseDTO response = new OcaResponseDTO(1L, "En camino");
        when(webClient.get().uri("https://api.oca.com/status/1").retrieve().bodyToMono(OcaResponseDTO.class).block())
                .thenReturn(response);

        OcaResponseDTO result = deliveryService.getOcaStatus(1L);
        assertNotNull(result);
        assertEquals("En camino", result.getStatus());
    }


    /**
     * Prueba el método getCorreoArgentinoStatus del DeliveryServiceImpl.
     */
    @Test
    public void testGetCorreoArgentinoStatus() {
        CorreoArgentinoResponseDTO response = new CorreoArgentinoResponseDTO(1L, "En camino", "12345", "http://tracking.url");
        when(webClient.get().uri("https://api.correoargentino.com/status/1").retrieve().bodyToMono(CorreoArgentinoResponseDTO.class).block())
                .thenReturn(response);

        CorreoArgentinoResponseDTO result = deliveryService.getCorreoArgentinoStatus(1L);
        assertNotNull(result);
        assertEquals("En camino", result.getStatus());
    }
}