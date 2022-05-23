package com.myclin.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConsultaDTO {

	private Integer id;	
	private Integer idClinica;
	private Integer idFuncionario;
	private Integer idPaciente;
	private Integer idServico;
	private String atendimento;
	private String anexoUm;
	private String AnexoDois;
}
