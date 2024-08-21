package com.eldar.logistica.controller;

import com.eldar.logistica.delivery.controllers.DeliveryController;
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

@ExtendWith(MockitoExtension.class)
public class DeliveryControllerTest {

    @Mock
    private AndreaniService andreaniService;

    @InjectMocks
    private DeliveryController deliveryController;

    private MockMvc mockMvc;
    private Andreani andreani;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(deliveryController).build();
        andreani = new Andreani();
        andreani.setId(1L);
        andreani.setStatus("En camino");
    }


    @Test
    public void testGetOrderStatus() throws Exception {
        when(andreaniService.findById(1L)).thenReturn(andreani);

        mockMvc.perform(get("/api/andreani/1/status"))
                .andExpect(status().isOk())
                .andExpect(content().string("En camino"));
    }


    @Test
    public void testUpdateOrderStatus() throws Exception {
        when(andreaniService.updateStatus(1L, "Entregado")).thenReturn(andreani);

        mockMvc.perform(post("/api/andreani/1/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Entregado\""))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("En camino"));
    }

}