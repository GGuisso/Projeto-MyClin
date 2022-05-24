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

import com.myclin.entity.Clinica;
import com.myclin.service.AgendaService;
import com.myclin.service.ClinicaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/clinica")
@Api("Clinica Controller")
@RequiredArgsConstructor
public class ClinicaController {

	private final ClinicaService clinicaService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere uma nova Clinica")
	public Clinica salvar(@RequestBody @Valid Clinica clinica) {
		return clinicaService.CreateClinica(clinica);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<Clinica> buscarPorId(@PathVariable Integer id) {
		return clinicaService.findClinicaById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todas as Clinicas")
	public List<Clinica> listarTodos(){
		return clinicaService.listAllClinica();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove uma Clinica por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return clinicaService.deleteClinicaById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza a Clinica")
	public ResponseEntity<Clinica> atualizar(@PathVariable Integer id, @RequestBody @Valid Clinica clinica) {
		return clinicaService.updateClinicaById(clinica, id);
	}
}
