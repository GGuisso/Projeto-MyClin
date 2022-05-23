package com.myclin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import com.myclin.dto.ConsultaDTO;
import com.myclin.entity.Clinica;
import com.myclin.entity.Consulta;
import com.myclin.entity.Funcionario;
import com.myclin.entity.Paciente;
import com.myclin.entity.Servico;
import com.myclin.repository.ClinicaRepository;
import com.myclin.repository.ConsultaRepository;
import com.myclin.repository.FuncionarioRepository;
import com.myclin.repository.PacienteRepository;
import com.myclin.repository.ServicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConsultaService {

	@Autowired
	private ConsultaRepository repository;
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	@Autowired
	private ClinicaRepository clinicaRepository;
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private ServicoRepository servicoRepository;
	
	public Consulta CreateConsulta(ConsultaDTO dto) {
		Integer idClinica = dto.getIdClinica();
		Clinica clinica =
				clinicaRepository
				.findById(idClinica)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Clinica não encontrada"));
		
		Integer idFuncionario = dto.getIdFuncionario();
		Funcionario funcionario =
				funcionarioRepository
				.findById(idFuncionario)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Funcionario não encontrado"));

		Integer idPaciente = dto.getIdPaciente();		
		Paciente paciente =
				pacienteRepository
				.findById(idPaciente)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Paciente não encontrada"));

		Integer idServico = dto.getIdServico();		
		Servico servico =
				servicoRepository
				.findById(idServico)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Servico não encontrada"));
		
		Consulta consulta = new Consulta();
		consulta.setClinica(clinica);
		consulta.setFuncionario(funcionario);
		consulta.setPaciente(paciente);
		consulta.setServico(servico);
		consulta.setAtendimento(dto.getAtendimento());
		consulta.setAnexoUm(dto.getAnexoUm());
		consulta.setAnexoDois(dto.getAnexoDois());
		
		return repository.save(consulta);
	}
	
	public List<Consulta> listAllConsulta(){
		return repository.findAll();
	}
	
	public ResponseEntity<Consulta> findConsultaById(Integer id){
		return repository
				.findById(id)
				.map(consulta -> ResponseEntity.ok().body(consulta))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado"));
	}
	
	public ResponseEntity<Consulta> updateConsultaById(ConsultaDTO consulta, Integer id){
		Integer idFuncionario = consulta.getIdFuncionario();
		Funcionario funcionario =
				funcionarioRepository
				.findById(idFuncionario)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Funcionario não encontrado"));

		Integer idPaciente = consulta.getIdPaciente();		
		Paciente paciente =
				pacienteRepository
				.findById(idPaciente)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Paciente não encontrada"));

		Integer idServico = consulta.getIdServico();		
		Servico servico =
				servicoRepository
				.findById(idServico)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Servico não encontrada"));
		
		return repository
				.findById(id)
				.map(consultaToUpdate ->{
					consultaToUpdate.setFuncionario(funcionario);
					consultaToUpdate.setPaciente(paciente);
					consultaToUpdate.setServico(servico);
					consultaToUpdate.setAtendimento(consulta.getAtendimento());
					consultaToUpdate.setAnexoUm(consulta.getAnexoUm());
					consultaToUpdate.setAnexoDois(consulta.getAnexoDois());
					Consulta update = repository.save(consultaToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado"));
	}
	
	public ResponseEntity<Object> deleteConsultaById(Integer id){
		return repository
		.findById(id)
		.map(consultaToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrado"));
	}
}
