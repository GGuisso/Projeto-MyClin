package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.dto.PacienteDTO;
import com.myclin.entity.Clinica;
import com.myclin.entity.Paciente;
import com.myclin.repository.ClinicaRepository;
import com.myclin.repository.PacienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteService {

	private final PacienteRepository repository;
	private final ClinicaRepository clinicaRepository;
	
	public Paciente CreatePaciente(PacienteDTO dto) {
		Integer idClinica = dto.getIdClinica();
		Clinica clinica =
				clinicaRepository
				.findById(idClinica)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Clinica não encontrada"));
		
		Paciente paciente = new Paciente();
		paciente.setClinica(clinica);
		paciente.setNome(dto.getNome());
		paciente.setDataNascimento(dto.getDataNascimento());
		paciente.setCpf(dto.getCpf());
		paciente.setTelefone(dto.getTelefone());
		paciente.setEmail(dto.getEmail());
		paciente.setCep(dto.getCep());
		paciente.setEndereco(dto.getEndereco());
		paciente.setNumeroEndereco(dto.getNumeroEndereco());
		paciente.setComplemento(dto.getComplemento());
		
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
					pacienteToUpdate.setNumeroEndereco(paciente.getNumeroEndereco());
					pacienteToUpdate.setComplemento(paciente.getComplemento());
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
