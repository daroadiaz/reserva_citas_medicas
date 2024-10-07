package com.example.reserva_citas_medicas.controller;

import com.example.reserva_citas_medicas.model.CitaMedica;
import com.example.reserva_citas_medicas.service.CitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/citas")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping
    public List<CitaMedica> getAllCitas() {
        return citaMedicaService.getAllCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CitaMedica>> getCitaById(@PathVariable Long id) {
        return citaMedicaService.getCitaById(id)
                .map(citaMedica -> EntityModel.of(citaMedica,
                        linkTo(methodOn(CitaMedicaController.class).getCitaById(id)).withSelfRel(),
                        linkTo(methodOn(CitaMedicaController.class).getAllCitas()).withRel("citas")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CitaMedica createCita(@RequestBody CitaMedica citaMedica) {
        return citaMedicaService.saveCita(citaMedica);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaMedica> updateCita(@PathVariable Long id, @RequestBody CitaMedica citaMedicaDetails) {
        return citaMedicaService.getCitaById(id)
                .map(citaMedica -> {
                    citaMedica.setPaciente(citaMedicaDetails.getPaciente());
                    citaMedica.setDoctor(citaMedicaDetails.getDoctor());
                    citaMedica.setFecha(citaMedicaDetails.getFecha());
                    citaMedica.setHora(citaMedicaDetails.getHora());
                    citaMedica.setMotivo(citaMedicaDetails.getMotivo());
                    citaMedica.setEstado(citaMedicaDetails.getEstado());
                    return ResponseEntity.ok(citaMedicaService.saveCita(citaMedica));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        return citaMedicaService.getCitaById(id)
                .map(citaMedica -> {
                    citaMedicaService.deleteCita(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}