package com.eldar.logistica.services;

import com.eldar.logistica.delivery.domain.entities.Andreani;
import com.eldar.logistica.delivery.services.impl.AndreaniServiceImpl;
import com.eldar.logistica.delivery.domain.repositories.AndreaniRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AndreaniServiceImplTest {

    @Mock
    private AndreaniRepository andreaniRepository;

    @InjectMocks
    private AndreaniServiceImpl andreaniService;

    private Andreani andreani;



    @BeforeEach
    public void setUp() {
        andreani = new Andreani();
        andreani.setId(1L);
        andreani.setStatus("En camino");
    }


    @Test
    public void testFindById() {
        when(andreaniRepository.findById(1L)).thenReturn(java.util.Optional.of(andreani));

        Andreani result = andreaniService.findById(1L);
        assertNotNull(result);
        assertEquals("En camino", result.getStatus());
    }


    @Test
    public void testUpdateStatus() {
        when(andreaniRepository.findById(1L)).thenReturn(java.util.Optional.of(andreani));
        when(andreaniRepository.save(any(Andreani.class))).thenReturn(andreani);

        Andreani result = andreaniService.updateStatus(1L, "Entregado");
        assertNotNull(result);
        assertEquals("Entregado", result.getStatus());
    }


}