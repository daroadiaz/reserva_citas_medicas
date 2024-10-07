package com.example.reserva_citas_medicas.service;

import com.example.reserva_citas_medicas.model.CitaMedica;
import com.example.reserva_citas_medicas.repository.CitaMedicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CitaMedicaServiceTest {

    @Mock
    private CitaMedicaRepository citaMedicaRepository;

    @InjectMocks
    private CitaMedicaService citaMedicaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCitas() {
        CitaMedica cita1 = new CitaMedica();
        CitaMedica cita2 = new CitaMedica();
        when(citaMedicaRepository.findAll()).thenReturn(Arrays.asList(cita1, cita2));

        List<CitaMedica> citas = citaMedicaService.getAllCitas();

        assertEquals(2, citas.size());
        verify(citaMedicaRepository, times(1)).findAll();
    }

    @Test
    void getCitaById() {
        CitaMedica cita = new CitaMedica();
        cita.setId(1L);
        when(citaMedicaRepository.findById(1L)).thenReturn(Optional.of(cita));

        Optional<CitaMedica> result = citaMedicaService.getCitaById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
    }

    @Test
    void saveCita() {
        CitaMedica cita = new CitaMedica();
        cita.setFecha(LocalDate.now());
        cita.setHora(LocalTime.now());
        when(citaMedicaRepository.save(any(CitaMedica.class))).thenReturn(cita);

        CitaMedica savedCita = citaMedicaService.saveCita(cita);

        assertNotNull(savedCita);
        assertEquals(cita.getFecha(), savedCita.getFecha());
        verify(citaMedicaRepository, times(1)).save(cita);
    }

    @Test
    void deleteCita() {
        citaMedicaService.deleteCita(1L);
        verify(citaMedicaRepository, times(1)).deleteById(1L);
    }
}