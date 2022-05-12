package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgendaDTO {

	private Integer id;
	private Integer idFuncionario;
	private String dataAtendimento;
	private String hora;
	private String paciente;
	private String status;
}
