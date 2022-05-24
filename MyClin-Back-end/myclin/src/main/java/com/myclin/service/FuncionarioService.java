package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.dto.FuncionarioDTO;
import com.myclin.entity.Clinica;
import com.myclin.entity.Funcao;
import com.myclin.entity.Funcionario;
import com.myclin.repository.ClinicaRepository;
import com.myclin.repository.FuncaoRepository;
import com.myclin.repository.FuncionarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

	private final FuncionarioRepository repository;
	private final ClinicaRepository clinicaRepository;
	private final FuncaoRepository funcaoRepository;
	
	public Funcionario CreateFuncionario(FuncionarioDTO dto) {
		Integer idClinica = dto.getIdClinica();
		Clinica clinica =
				clinicaRepository
				.findById(idClinica)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Clinica não encontrada"));
		
		Integer idFuncao = dto.getIdFuncao();
		Funcao funcao =
				funcaoRepository
				.findById(idFuncao)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Clinica não encontrada"));
		
		Funcionario funcionario = new Funcionario();
		funcionario.setClinica(clinica);
		funcionario.setFuncao(funcao);
		funcionario.setNome(dto.getNome());
		funcionario.setDataNascimento(dto.getDataNascimento());
		funcionario.setCpf(dto.getCpf());
		funcionario.setTelefone(dto.getTelefone());
		funcionario.setEmail(dto.getEmail());
		funcionario.setCep(dto.getCep());
		funcionario.setEndereco(dto.getEndereco());
		funcionario.setNumeroEndereco(dto.getNumeroEndereco());
		funcionario.setComplemento(dto.getComplemento());
		
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
