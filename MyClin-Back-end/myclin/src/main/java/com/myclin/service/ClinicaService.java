package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.entity.Clinica;
import com.myclin.repository.ClinicaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClinicaService {

	private final ClinicaRepository repository;
	
	public Clinica CreateClinica(Clinica clinica) {
		return repository.save(clinica);
	}
	
	public List<Clinica> listAllClinica(){
		return repository.findAll();
	}
	
	public ResponseEntity<Clinica> findClinicaById(Integer id){
		return repository
				.findById(id)
				.map(clinica -> ResponseEntity.ok().body(clinica))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica não encontrado"));
	}
	
	public ResponseEntity<Clinica> updateClinicaById(Clinica clinica, Integer id){
		return repository
				.findById(id)
				.map(clinicaToUpdate ->{
					clinicaToUpdate.setNome(clinica.getNome());
					clinicaToUpdate.setCnpj(clinica.getCnpj());
					clinicaToUpdate.setTelefone(clinica.getTelefone());
					clinicaToUpdate.setEmail(clinica.getEmail());
					clinicaToUpdate.setEndereco(clinica.getEndereco());
					Clinica update = repository.save(clinicaToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica não encontrado"));
	}
	
	public ResponseEntity<Object> deleteClinicaById(Integer id){
		return repository
		.findById(id)
		.map(clinicaToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Clinica não encontrado"));
	}
}
