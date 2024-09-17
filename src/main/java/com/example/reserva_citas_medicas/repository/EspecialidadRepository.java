package com.example.reserva_citas_medicas.repository;

import com.example.reserva_citas_medicas.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {}


