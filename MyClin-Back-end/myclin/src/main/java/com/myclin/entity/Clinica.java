package com.myclin.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clinica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotNull(message = "Nome é campo obrigatório")
	private String nome;
		
	@Column(nullable = false, length = 20)
	@NotNull(message = "cnpj é campo obrigatório")
	private String cnpj;
	
	@Column(nullable = false, length = 11)
	private String telefone;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	@Column(length = 9)
	private String cep;
	
	@Column(length = 50)
	private String endereco;
	
	@Column(name = "num_endereco")
	private int numeroEndereco;
	
	@Column(length = 50)
	private String complemento;
	
	@Column(name = "data_cadastro")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clinica")
	private List<Funcionario> funcionario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clinica")
	private List<Paciente> paciente;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clinica")
	private List<User> user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "clinica")
	private List<Servico> servico;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
}
