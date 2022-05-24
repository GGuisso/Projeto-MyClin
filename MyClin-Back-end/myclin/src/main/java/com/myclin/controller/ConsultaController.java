package com.myclin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myclin.dto.ConsultaDTO;
import com.myclin.entity.Consulta;
import com.myclin.service.AgendaService;
import com.myclin.service.ConsultaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/consulta")
@Api("Consulta Controller")
@RequiredArgsConstructor
public class ConsultaController {

	private final ConsultaService consultaService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere uma nova Consulta")
	public Consulta salvar(@RequestBody @Valid ConsultaDTO consulta) {
		return consultaService.CreateConsulta(consulta);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<Consulta> buscarPorId(@PathVariable Integer id) {
		return consultaService.findConsultaById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todas as Consultas")
	public List<Consulta> listarTodos(){
		return consultaService.listAllConsulta();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove uma Consulta por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return consultaService.deleteConsultaById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza a Consulta")
	public ResponseEntity<Consulta> atualizar(@PathVariable Integer id, @RequestBody @Valid ConsultaDTO Consulta) {
		return consultaService.updateConsultaById(Consulta, id);
	}
}
