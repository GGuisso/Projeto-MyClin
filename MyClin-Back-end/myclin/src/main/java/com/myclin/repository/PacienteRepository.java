package com.myclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

}
