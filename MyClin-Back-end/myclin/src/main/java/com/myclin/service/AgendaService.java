package com.myclin.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.myclin.dto.AgendaDTO;
import com.myclin.entity.Agenda;
import com.myclin.entity.Funcionario;
import com.myclin.repository.AgendaRepository;
import com.myclin.repository.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AgendaService {

	@Autowired
	private AgendaRepository repository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Agenda CreateAgenda(AgendaDTO dto) {
		LocalDate data = LocalDate.parse(dto.getDataAtendimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalTime hora = LocalTime.parse(dto.getHora(), DateTimeFormatter.ofPattern("HH:mm"));
		Integer idFuncionario = dto.getIdFuncionario();
		Funcionario funcionario =
				funcionarioRepository
				.findById(idFuncionario)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Funcionario não encontrado"));
		
		Agenda agenda = new Agenda();
		agenda.setFuncionario(funcionario);
		agenda.setDataAtendimento(data);
		agenda.setHora(hora);
		agenda.setStatus(dto.getStatus());
		
		return repository.save(agenda);
	}
	
	public List<Agenda> listAllAgenda(){
		return repository.findAll();
	}
	
	public ResponseEntity<Agenda> findAgendaById(Integer id){
		return repository
				.findById(id)
				.map(agenda -> ResponseEntity.ok().body(agenda))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda não encontrado"));
	}
	
	public ResponseEntity<Agenda> updateAgendaById(AgendaDTO agenda, Integer id){
		LocalDate data = LocalDate.parse(agenda.getDataAtendimento(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		LocalTime hora = LocalTime.parse(agenda.getHora(), DateTimeFormatter.ofPattern("HH:mm"));
		Integer idFuncionario = agenda.getIdFuncionario();
		Funcionario funcionario =
				funcionarioRepository
				.findById(idFuncionario)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Funcionario não encontrado"));
		
		return repository
				.findById(id)
				.map(agendaToUpdate ->{
					agendaToUpdate.setFuncionario(funcionario);;
					agendaToUpdate.setDataAtendimento(data);
					agendaToUpdate.setHora(hora);
					agendaToUpdate.setStatus(agenda.getStatus());
					Agenda update = repository.save(agendaToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda não encontrado"));
	}
	
	public ResponseEntity<Object> deleteAgendaById(Integer id){
		return repository
		.findById(id)
		.map(agendaToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Agenda não encontrado"));
	}
}
