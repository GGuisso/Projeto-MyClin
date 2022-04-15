package com.myclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

}
