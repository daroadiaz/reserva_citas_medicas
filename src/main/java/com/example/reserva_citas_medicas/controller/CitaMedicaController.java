package com.example.reserva_citas_medicas.controller;

import com.example.reserva_citas_medicas.model.CitaMedica;
import com.example.reserva_citas_medicas.service.CitaMedicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/citas")
public class CitaMedicaController {

    @Autowired
    private CitaMedicaService citaMedicaService;

    @GetMapping
    public CollectionModel<EntityModel<CitaMedica>> getAllCitas() {
        List<EntityModel<CitaMedica>> citas = citaMedicaService.getAllCitas().stream()
                .map(cita -> EntityModel.of(cita,
                        linkTo(methodOn(CitaMedicaController.class).getCitaById(cita.getId())).withSelfRel(),
                        linkTo(methodOn(CitaMedicaController.class).getAllCitas()).withRel("citas")))
                .collect(Collectors.toList());

        return CollectionModel.of(citas, linkTo(methodOn(CitaMedicaController.class).getAllCitas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CitaMedica>> getCitaById(@PathVariable Long id) {
        return citaMedicaService.getCitaById(id)
                .map(cita -> EntityModel.of(cita,
                        linkTo(methodOn(CitaMedicaController.class).getCitaById(id)).withSelfRel(),
                        linkTo(methodOn(CitaMedicaController.class).getAllCitas()).withRel("citas")))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EntityModel<CitaMedica>> createCita(@RequestBody CitaMedica citaMedica) {
        CitaMedica nuevaCita = citaMedicaService.saveCita(citaMedica);
        EntityModel<CitaMedica> entityModel = EntityModel.of(nuevaCita,
                linkTo(methodOn(CitaMedicaController.class).getCitaById(nuevaCita.getId())).withSelfRel(),
                linkTo(methodOn(CitaMedicaController.class).getAllCitas()).withRel("citas"));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CitaMedica>> updateCita(@PathVariable Long id,
            @RequestBody CitaMedica citaMedicaDetails) {
        return citaMedicaService.getCitaById(id)
                .map(citaMedica -> {
                    citaMedica.setPaciente(citaMedicaDetails.getPaciente());
                    citaMedica.setDoctor(citaMedicaDetails.getDoctor());
                    citaMedica.setFecha(citaMedicaDetails.getFecha());
                    citaMedica.setHora(citaMedicaDetails.getHora());
                    citaMedica.setMotivo(citaMedicaDetails.getMotivo());
                    citaMedica.setEstado(citaMedicaDetails.getEstado());
                    CitaMedica updatedCita = citaMedicaService.saveCita(citaMedica);
                    EntityModel<CitaMedica> entityModel = EntityModel.of(updatedCita,
                            linkTo(methodOn(CitaMedicaController.class).getCitaById(updatedCita.getId())).withSelfRel(),
                            linkTo(methodOn(CitaMedicaController.class).getAllCitas()).withRel("citas"));
                    return ResponseEntity.ok(entityModel);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        return citaMedicaService.getCitaById(id)
                .map(citaMedica -> {
                    citaMedicaService.deleteCita(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}