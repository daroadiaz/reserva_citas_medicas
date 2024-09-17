package com.example.reserva_citas_medicas.controller;

import com.example.reserva_citas_medicas.model.Paciente;
import com.example.reserva_citas_medicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.getAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.getPacienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.savePaciente(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody Paciente pacienteDetails) {
        return pacienteService.getPacienteById(id)
                .map(paciente -> {
                    paciente.setNombre(pacienteDetails.getNombre());
                    paciente.setApellido(pacienteDetails.getApellido());
                    paciente.setFechaNacimiento(pacienteDetails.getFechaNacimiento());
                    paciente.setTelefono(pacienteDetails.getTelefono());
                    paciente.setEmail(pacienteDetails.getEmail());
                    return ResponseEntity.ok(pacienteService.savePaciente(paciente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        return pacienteService.getPacienteById(id)
                .map(paciente -> {
                    pacienteService.deletePaciente(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}