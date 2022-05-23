package com.myclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.Consulta;


public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {

}
