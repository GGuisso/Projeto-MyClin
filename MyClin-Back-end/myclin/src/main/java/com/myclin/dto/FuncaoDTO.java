package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FuncaoDTO {

	private Integer id;
	private String funcao;
	private Integer idClinica;
}
