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

import com.myclin.entity.Paciente;
import com.myclin.service.PacienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/pacientes")
@Api("Paciente Controller")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere um novo Paciente")
	public Paciente salvar(@RequestBody @Valid Paciente paciente) {
		return pacienteService.CreatePaciente(paciente);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<Paciente> buscarPorId(@PathVariable Integer id) {
		return pacienteService.findPacienteById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todos os pacientes")
	public List<Paciente> listarTodos(){
		return pacienteService.listAllPaciente();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove um paciente por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return pacienteService.deletePacienteById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza o paciente")
	public ResponseEntity<Paciente> atualizar(@PathVariable Integer id, @RequestBody @Valid Paciente paciente) {
		return pacienteService.updatePacienteById(paciente, id);
	}
}
