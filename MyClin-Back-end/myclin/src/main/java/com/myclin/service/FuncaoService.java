package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.entity.Funcao;
import com.myclin.repository.FuncaoRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncaoService {

	@Autowired
	private FuncaoRepository repository;
	
	public Funcao CreateFuncao(Funcao funcao) {
		return repository.save(funcao);
	}
	
	public List<Funcao> listAllFuncao(){
		return repository.findAll();
	}
	
	public ResponseEntity<Funcao> findFuncaoById(Integer id){
		return repository
				.findById(id)
				.map(funcao -> ResponseEntity.ok().body(funcao))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcao não encontrado"));
	}
	
	public ResponseEntity<Funcao> updateFuncaoById(Funcao funcao, Integer id){
		return repository
				.findById(id)
				.map(funcaoToUpdate ->{
					funcaoToUpdate.setFuncao(funcao.getFuncao());
					Funcao update = repository.save(funcaoToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcao não encontrado"));
	}
	
	public ResponseEntity<Object> deleteFuncaoById(Integer id){
		return repository
		.findById(id)
		.map(funcaoToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Funcao não encontrado"));
	}
}
