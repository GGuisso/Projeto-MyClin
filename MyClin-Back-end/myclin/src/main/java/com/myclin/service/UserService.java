package com.myclin.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.dto.UserDTO;
import com.myclin.entity.Clinica;
import com.myclin.entity.User;
import com.myclin.repository.ClinicaRepository;
import com.myclin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository repository;
	private final ClinicaRepository clinicaRepository;
	
	public User CreateUser(UserDTO dto) {
		Integer idClinica = dto.getIdClinica();
		Clinica clinica =
				clinicaRepository
				.findById(idClinica)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Clinica não encontrada"));
		
		User user = new User();
		user.setClinica(clinica);
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());

		return repository.save(user);
	}
	
	public List<User> listAllUser(){
		return repository.findAll();
	}
	
	public ResponseEntity<User> findUserById(Integer id){
		return repository
				.findById(id)
				.map(user -> ResponseEntity.ok().body(user))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User não encontrado"));
	}
	
	public ResponseEntity<User> updateUserById(User user, Integer id){
				return repository
				.findById(id)
				.map(userToUpdate ->{
					userToUpdate.setUsername(user.getUsername());
					userToUpdate.setPassword(user.getPassword());
					User update = repository.save(userToUpdate);
					return ResponseEntity.ok().body(update);})
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User não encontrado"));
	}
	
	public ResponseEntity<Object> deleteUserById(Integer id){
		return repository
		.findById(id)
		.map(userToDelete ->{
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User não encontrado"));
	}

}
