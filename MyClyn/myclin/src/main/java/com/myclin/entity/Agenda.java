package com.myclin.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agenda {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;
	
	@Column(name = "data_atendimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAtendimento;
	
	@Column(length = 50)
	private String modelo;
	
	@Column(name = "hora_inicio")
	@JsonFormat(pattern = "hh:mm")
	private LocalTime horaInicio;
	
	@Column(name = "hora_fim")
	@JsonFormat(pattern = "hh:mm")
	private LocalTime horaFim;
	
}
