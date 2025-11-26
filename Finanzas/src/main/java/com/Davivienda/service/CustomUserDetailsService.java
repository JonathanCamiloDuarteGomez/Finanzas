package com.Davivienda.service;

import com.Davivienda.model.Usuario;
import com.Davivienda.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

private final UsuarioRepository usuarioRepository;

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Usuario usuario = usuarioRepository.findByEmail(email)
			                  .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con email: " + email));
	
	return new User(
			usuario.getEmail(),
			usuario.getClaveHash(),
			true, true, true, true, // enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
			usuario.getRoles().stream()
					.map(rol -> new SimpleGrantedAuthority("ROLE_" + rol))
					.collect(Collectors.toList())
	);
}
}
