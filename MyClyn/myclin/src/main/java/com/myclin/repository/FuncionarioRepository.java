package com.myclin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclin.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
