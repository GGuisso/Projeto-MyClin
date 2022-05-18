package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgendaDTO {

	private Integer id;	
	private Integer idClinica;
	private Integer idFuncionario;
	private Integer idConsulta;
	private String dataAtendimento;
	private String hora;
	private String status;
}
