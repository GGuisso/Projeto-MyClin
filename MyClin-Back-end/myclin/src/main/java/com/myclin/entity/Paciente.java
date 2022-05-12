package com.myclin.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

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
public class Paciente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotNull(message = "Nome é campo obrigatório")
	private String nome;
	
	@Column(name = "data_nascimento", nullable = false, length = 9)
	private String dataNascimento;
	
	@Column(nullable = false, length = 20)
	@NotNull(message = "Cpf é campo obrigatório")
	private String cpf;
	
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
	
	@ManyToOne
	@JoinColumn(name = "clinica_id")
	private Clinica clinica;
	
	@OneToMany(mappedBy = "paciente")
	private List<Consulta> consulta;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
}
