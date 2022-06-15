package com.myclin.service;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.myclin.dto.UserDTO;
import com.myclin.entity.Clinica;
import com.myclin.entity.Usuario;
import com.myclin.repository.ClinicaRepository;
import com.myclin.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService{
	
	private final UsuarioRepository repository;
	private final ClinicaRepository clinicaRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository
				.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Login não encontrado."));
				
		return User
				.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles("USER")
				.build();
	}
	
	public Usuario CreateUser(UserDTO dto) {
		Integer idClinica = dto.getIdClinica();
		Clinica clinica =
				clinicaRepository
				.findById(idClinica)
				.orElseThrow(() ->
						new ResponseStatusException(
								HttpStatus.BAD_REQUEST, "Clinica não encontrada"));
		
		Usuario usuario = new Usuario();
		usuario.setClinica(clinica);
		usuario.setUsername(dto.getUsername());
		usuario.setPassword(dto.getPassword());

		return repository.save(usuario);
	}
	
	public List<Usuario> listAllUser(){
		return repository.findAll();
	}
	
	public ResponseEntity<Usuario> findUserById(Integer id){
		return repository
				.findById(id)
				.map(user -> ResponseEntity.ok().body(user))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User não encontrado"));
	}
	
	public ResponseEntity<Usuario> updateUserById(Usuario usuario, Integer id){
				return repository
				.findById(id)
				.map(userToUpdate ->{
					userToUpdate.setUsername(usuario.getUsername());
					userToUpdate.setPassword(usuario.getPassword());
					Usuario update = repository.save(userToUpdate);
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
