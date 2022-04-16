package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgendaDTO {

	private Integer id;
	private Integer idFuncionario;
	private String dataAtendimento;
	private String modelo;
	private String horaInicio;
	private String horaFim;
}
