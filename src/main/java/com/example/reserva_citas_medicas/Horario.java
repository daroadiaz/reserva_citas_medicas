package com.example.reserva_citas_medicas;

import java.util.Date;

public class Horario {
    private Date fecha;
    private String doctor;

    public Horario(Date fecha, String doctor) {
        this.fecha = fecha;
        this.doctor = doctor;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
