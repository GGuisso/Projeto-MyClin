package com.myclin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consulta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 255)
	private String atendimento;
	
	@Column(length = 255)
	private String anexoUm;
	
	@Column(length = 255)
	private String AnexoDois;
	
	@ManyToOne
	@JoinColumn(name = "clinica_id")
	private Clinica clinica;
	
	@ManyToOne
	@JoinColumn(name = "servico_id")
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	@OneToOne(mappedBy = "consulta")
	private Agenda agenda;
}
