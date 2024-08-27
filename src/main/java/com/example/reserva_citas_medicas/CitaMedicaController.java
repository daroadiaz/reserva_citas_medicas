package com.example.reserva_citas_medicas;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/citas")
public class CitaMedicaController {

    private List<CitaMedica> citas = new ArrayList<>();
    private List<Horario> horariosDisponibles = new ArrayList<>();

    public CitaMedicaController() {
        // Datos dummy para citas médicas con idCita específicos
        citas.add(new CitaMedica("1", "Juan Pérez", new Date(), "Dr. Pérez"));
        citas.add(new CitaMedica("2", "María López", new Date(), "Dra. Gómez"));

        // Datos dummy para horarios disponibles
        horariosDisponibles.add(new Horario(new Date(), "Dr. Pérez"));
        horariosDisponibles.add(new Horario(new Date(), "Dra. Gómez"));
    }

    @GetMapping
    public List<CitaMedica> getCitas() {
        return citas;
    }

    @GetMapping("/{idCita}")
    public CitaMedica getCitaById(@PathVariable String idCita) {
        return citas.stream().filter(cita -> cita.getIdCita().equals(idCita)).findFirst().orElse(null);
    }

    @GetMapping("/horarios")
    public List<Horario> getHorariosDisponibles() {
        return horariosDisponibles;
    }

    @PostMapping
    public CitaMedica crearCita(@RequestBody CitaMedica cita) {
        citas.add(cita);
        return cita;
    }
}
