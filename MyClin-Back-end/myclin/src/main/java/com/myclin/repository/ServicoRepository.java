package com.myclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
