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
        citas.add(new CitaMedica("1", "Juan Pérez",
                new Date(), "Doctora Juanita"));
        citas.add(new CitaMedica("2", "María López",
                new Date(), "Doctor Fernando"));
        horariosDisponibles.add(new Horario(new Date(), "Doctora Juanita"));
        horariosDisponibles.add(new Horario(new Date(), "Doctor Fernando"));
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

}
