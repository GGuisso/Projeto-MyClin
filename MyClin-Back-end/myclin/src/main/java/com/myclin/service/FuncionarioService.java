package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.entity.Funcionario;
import com.myclin.repository.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	public Funcionario CreateFuncionario(Funcionario funcionario) {
		return repository.save(funcionario);
	}
	
	public List<Funcionario> listAllFuncionario(){
		return repository.findAll();
	}
	
	public ResponseEntity<Funcionario> findFuncionarioById(Integer id){
		return repository
				.findById(id)
				.map(funcionario -> ResponseEntity.ok().body(funcionario))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));
	}
	
	public ResponseEntity<Funcionario> updateFuncionarioById(Funcionario funcionario, Integer id){
		return repository
				.findById(id)
				.map(funcionarioToUpdate ->{
					funcionarioToUpdate.setNome(funcionario.getNome());
					funcionarioToUpdate.setCpf(funcionario.getCpf());
					funcionarioToUpdate.setDataNascimento(funcionario.getDataNascimento());
					funcionarioToUpdate.setTelefone(funcionario.getTelefone());
					funcionarioToUpdate.setEmail(funcionario.getEmail());
					funcionarioToUpdate.setEndereco(funcionario.getEndereco());
					funcionarioToUpdate.setFuncao(funcionario.getFuncao());
					Funcionario update = repository.save(funcionarioToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));
	}
	
	public ResponseEntity<Object> deleteFuncionarioById(Integer id){
		return repository
		.findById(id)
		.map(funcionarioToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcionario não encontrado"));
	}
}
