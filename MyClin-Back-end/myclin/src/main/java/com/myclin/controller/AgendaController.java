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

import com.myclin.dto.AgendaDTO;
import com.myclin.entity.Agenda;
import com.myclin.service.AgendaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/agenda")
@Api("Agenda Controller")
@RequiredArgsConstructor
public class AgendaController {

	private final AgendaService agendaService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere uma nova Agenda")
	public Agenda salvar(@RequestBody @Valid AgendaDTO agenda) {
		return agendaService.CreateAgenda(agenda);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<Agenda> buscarPorId(@PathVariable Integer id) {
		return agendaService.findAgendaById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todas as Agendas")
	public List<Agenda> listarTodos(){
		return agendaService.listAllAgenda();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove uma Agenda por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return agendaService.deleteAgendaById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza a Agenda")
	public ResponseEntity<Agenda> atualizar(@PathVariable Integer id, @RequestBody @Valid AgendaDTO Agenda) {
		return agendaService.updateAgendaById(Agenda, id);
	}
}
