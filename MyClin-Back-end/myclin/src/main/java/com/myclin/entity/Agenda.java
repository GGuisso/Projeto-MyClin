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
import javax.persistence.OneToOne;

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
	@Column(name = "idagenda")
	private Integer id;
	
	@Column(name = "data_atendimento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAtendimento;
	
	@Column(name = "hora")
	@JsonFormat(pattern = "hh:mm")
	private LocalTime hora;
	
	@Column(length = 50)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "clinica_id")
	private Clinica clinica;
	
	@OneToOne
	@JoinColumn(name = "consulta_id")
	private Consulta consulta;
	
}
