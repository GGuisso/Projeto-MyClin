package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.myclin.entity.Sala;
import com.myclin.repository.SalaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalaService {

	@Autowired
	private SalaRepository repository;
	
	public Sala CreateSala(Sala sala) {
		return repository.save(sala);
	}
	
	public List<Sala> listAllSala(){
		return repository.findAll();
	}
	
	public ResponseEntity<Sala> findSalaById(Integer id){
		return repository
				.findById(id)
				.map(sala -> ResponseEntity.ok().body(sala))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrado"));
	}
	
	public ResponseEntity<Sala> updateSalaById(Sala sala, Integer id){
		return repository
				.findById(id)
				.map(salaToUpdate ->{
					salaToUpdate.setNome(sala.getNome());
					salaToUpdate.setDescricao(sala.getDescricao());
					Sala update = repository.save(salaToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrado"));
	}
	
	public ResponseEntity<Object> deleteSalaById(Integer id){
		return repository
		.findById(id)
		.map(salaToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sala não encontrado"));
	}
}
