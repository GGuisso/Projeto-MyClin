package com.myclin.controller;

import com.myclin.dto.UserDTO;
import com.myclin.entity.User;
import com.myclin.service.UserService;

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
public class UserController {


	private final UserService userService;
	
	@PostMapping("salvar")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("Insere uma nova User")
	public User salvar(@RequestBody @Valid UserDTO user) {
		return userService.CreateUser(user);
	}
	
	@GetMapping("buscar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Busca por ID")
	public ResponseEntity<User> buscarPorId(@PathVariable Integer id) {
		return userService.findUserById(id);
	}
	
	@GetMapping("listar")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Lista todas as Users")
	public List<User> listarTodos(){
		return userService.listAllUser();
	}
	
	@DeleteMapping("deletar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("Remove uma User por ID")
	public ResponseEntity<Object> excluir(@PathVariable Integer id) {
		return userService.deleteUserById(id);	
	}
	
	@PutMapping("atualizar/{id}")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation("Atualiza a User")
	public ResponseEntity<User> atualizar(@PathVariable Integer id, @RequestBody @Valid User User) {
		return userService.updateUserById(User, id);
	}
}
