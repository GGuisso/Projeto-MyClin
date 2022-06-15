package com.myclin.controller;

import com.myclin.dto.UserDTO;
import com.myclin.entity.Usuario;
import com.myclin.service.UsuarioService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@Api("User Controller")
@RequiredArgsConstructor
public class UsuarioController {


	private final UsuarioService usuarioService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere uma nova User")
	public Usuario salvar(@RequestBody @Valid UserDTO user) {
		return usuarioService.CreateUser(user);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
		return usuarioService.findUserById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todas as Users")
	public List<Usuario> listarTodos(){
		return usuarioService.listAllUser();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove uma User por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return usuarioService.deleteUserById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza a User")
	public ResponseEntity<Usuario> atualizar(@PathVariable Integer id, @RequestBody @Valid Usuario Usuario) {
		return usuarioService.updateUserById(Usuario, id);
	}
}
