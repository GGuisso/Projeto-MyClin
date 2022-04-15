package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import com.myclin.entity.Paciente;
import com.myclin.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

	@Autowired
	private PacienteRepository repository;
	
	public Paciente CreatePaciente(Paciente paciente) {
		return repository.save(paciente);
	}
	
	public List<Paciente> listAllPaciente(){
		return repository.findAll();
	}
	
	public ResponseEntity<Paciente> findPacienteById(Integer id){
		return repository
				.findById(id)
				.map(paciente -> ResponseEntity.ok().body(paciente))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
	}
	
	public ResponseEntity<Paciente> updatePacienteById(Paciente paciente, Integer id){
		return repository
				.findById(id)
				.map(pacienteToUpdate ->{
					pacienteToUpdate.setNome(paciente.getNome());
					pacienteToUpdate.setDataNascimento(paciente.getDataNascimento());
					pacienteToUpdate.setCpf(paciente.getCpf());
					pacienteToUpdate.setTelefone(paciente.getTelefone());
					pacienteToUpdate.setEmail(paciente.getEmail());
					pacienteToUpdate.setEndereco(paciente.getEndereco());
					Paciente update = repository.save(pacienteToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
	}
	
	public ResponseEntity<Object> deletePacienteById(Integer id){
		return repository
		.findById(id)
		.map(pacienteToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));
	}
}
