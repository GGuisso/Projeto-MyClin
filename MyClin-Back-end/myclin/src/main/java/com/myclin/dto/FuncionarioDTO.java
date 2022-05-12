package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FuncionarioDTO {

	private Integer id;
	private Integer idClinica;
	private Integer idFuncao;
	private String nome;
	private String dataNascimento;
	private String cpf;
	private String telefone;
	private String email;
	private String cep;
	private String endereco;
	private Integer numeroEndereco;
	private String complemento;
}
