package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.dto.ServicoDTO;
import com.myclin.entity.Servico;
import com.myclin.repository.ServicoRepository;
import com.myclin.util.Converter;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServicoService {

	private final ServicoRepository repository;
	private final Converter converter;
	
	public Servico CreateServico(ServicoDTO dto) {
		
		Servico servico = new Servico();
		servico.setNome(dto.getNome());
		servico.setDescricao(dto.getDescricao());
		servico.setValor(converter.converter(dto.getValor()));
		return repository.save(servico);
	}
	
	public List<Servico> listAllServico(){
		return repository.findAll();
	}
	
	public ResponseEntity<Servico> findServicoById(Integer id){
		return repository
				.findById(id)
				.map(servico -> ResponseEntity.ok().body(servico))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
	}
	
	public ResponseEntity<Servico> updateServicoById(ServicoDTO servico, Integer id){
		return repository
				.findById(id)
				.map(servicoToUpdate ->{
					servicoToUpdate.setNome(servico.getNome());
					servicoToUpdate.setDescricao(servico.getDescricao());
					servicoToUpdate.setValor(converter.converter(servico.getValor()));
					Servico update = repository.save(servicoToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
	}
	
	public ResponseEntity<Object> deleteServicoById(Integer id){
		return repository
		.findById(id)
		.map(servicoToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Servico não encontrado"));
	}
}
