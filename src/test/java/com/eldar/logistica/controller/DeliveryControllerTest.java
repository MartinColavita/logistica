package com.eldar.logistica.controller;

import com.eldar.logistica.delivery.controllers.DeliveryController;
import com.eldar.logistica.delivery.model.response.AndreaniResponseDTO;
import com.eldar.logistica.delivery.model.response.CorreoArgentinoResponseDTO;
import com.eldar.logistica.delivery.model.response.OcaResponseDTO;
import com.eldar.logistica.delivery.services.contracts.DeliveryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Pruebas unitarias para la clase DeliveryController.
 */
@ExtendWith(MockitoExtension.class)
public class DeliveryControllerTest {

    @Mock
    private DeliveryService deliveryService;

    @InjectMocks
    private DeliveryController deliveryController;

    private MockMvc mockMvc;

    /**
     * Configura el entorno de prueba antes de cada prueba.
     */
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryController).build();
    }


    /**
     * Prueba el método getOrderStatusAndreani del DeliveryController.
     *
     */
    @Test
    public void testGetOrderStatusAndreani() throws Exception {
        AndreaniResponseDTO response = new AndreaniResponseDTO(1L, "En camino", "Mensaje", "12345");
        when(deliveryService.getAndreaniStatus(1L)).thenReturn(response);

        mockMvc.perform(get("/api/delivery/1/status/andreani"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("En camino"));
    }


    /**
     * Prueba el método getOrderStatusOca del DeliveryController.
     */
    @Test
    public void testGetOrderStatusOca() throws Exception {
        OcaResponseDTO response = new OcaResponseDTO(1L, "En camino");
        when(deliveryService.getOcaStatus(1L)).thenReturn(response);

        mockMvc.perform(get("/api/delivery/1/status/oca"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("En camino"));
    }


    /**
     * Prueba el método getOrderStatusCorreoArg del DeliveryController.
     */
    @Test
    public void testGetOrderStatusCorreoArg() throws Exception {
        CorreoArgentinoResponseDTO response = new CorreoArgentinoResponseDTO(1L, "En camino", "12345", "http://tracking.url");
        when(deliveryService.getCorreoArgentinoStatus(1L)).thenReturn(response);

        mockMvc.perform(get("/api/delivery/1/status/correoargentino"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("En camino"));
    }

}