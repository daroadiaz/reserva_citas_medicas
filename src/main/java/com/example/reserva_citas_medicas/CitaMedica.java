package com.example.reserva_citas_medicas;

import java.util.Date;

public class CitaMedica {
    private String idCita;
    private String paciente;
    private Date fecha;
    private String doctor;

    public CitaMedica(String idCita, String paciente, Date fecha, String doctor) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.fecha = fecha;
        this.doctor = doctor;
    }

    public String getIdCita() {
        return idCita;
    }

    public String getPaciente() {
        return paciente;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
