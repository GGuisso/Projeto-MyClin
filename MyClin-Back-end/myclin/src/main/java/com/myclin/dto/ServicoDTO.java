package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServicoDTO {

	private Integer id;
	private String nome;
	private String descricao;
	private String valor;
	
}
