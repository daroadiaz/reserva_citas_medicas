package com.example.reserva_citas_medicas.service;

import com.example.reserva_citas_medicas.model.CitaMedica;
import com.example.reserva_citas_medicas.repository.CitaMedicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CitaMedicaService {

    @Autowired
    private CitaMedicaRepository citaMedicaRepository;

    public List<CitaMedica> getAllCitas() {
        return citaMedicaRepository.findAll();
    }

    public Optional<CitaMedica> getCitaById(Long id) {
        return citaMedicaRepository.findById(id);
    }

    public CitaMedica saveCita(CitaMedica citaMedica) {
        return citaMedicaRepository.save(citaMedica);
    }

    public void deleteCita(Long id) {
        citaMedicaRepository.deleteById(id);
    }
}