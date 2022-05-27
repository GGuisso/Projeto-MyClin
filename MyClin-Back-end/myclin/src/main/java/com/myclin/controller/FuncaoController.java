package com.myclin.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.myclin.dto.FuncaoDTO;
import com.myclin.entity.Funcao;
import com.myclin.service.FuncaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/funcao")
@Api("Funcao Controller")
@RequiredArgsConstructor
public class FuncaoController {

	private final FuncaoService funcaoService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere uma nova Funcao")
	public Funcao salvar(@RequestBody @Valid FuncaoDTO funcao) {
		return funcaoService.CreateFuncao(funcao);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<Funcao> buscarPorId(@PathVariable Integer id) {
		return funcaoService.findFuncaoById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todas as Funcaos")
	public List<Funcao> listarTodos(){
		return funcaoService.listAllFuncao();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove uma Funcao por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return funcaoService.deleteFuncaoById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza a Funcao")
	public ResponseEntity<Funcao> atualizar(@PathVariable Integer id, @RequestBody @Valid Funcao Funcao) {
		return funcaoService.updateFuncaoById(Funcao, id);
	}
}
