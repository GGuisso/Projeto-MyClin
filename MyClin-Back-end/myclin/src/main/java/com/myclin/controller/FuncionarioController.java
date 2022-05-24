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

import com.myclin.dto.FuncionarioDTO;
import com.myclin.entity.Funcionario;
import com.myclin.service.AgendaService;
import com.myclin.service.FuncionarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/funcionarios")
@Api("Funcionario Controller")
@RequiredArgsConstructor
public class FuncionarioController {

	private final FuncionarioService funcionarioService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere uma nova Funcionario")
	public Funcionario salvar(@RequestBody @Valid FuncionarioDTO funcionario) {
		return funcionarioService.CreateFuncionario(funcionario);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<Funcionario> buscarPorId(@PathVariable Integer id) {
		return funcionarioService.findFuncionarioById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todas as Funcionarios")
	public List<Funcionario> listarTodos(){
		return funcionarioService.listAllFuncionario();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove uma Funcionario por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return funcionarioService.deleteFuncionarioById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza a Funcionario")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Integer id, @RequestBody @Valid Funcionario Funcionario) {
		return funcionarioService.updateFuncionarioById(Funcionario, id);
	}
}
