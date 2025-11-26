package com.Davivienda.service;


import com.Davivienda.DTO.JwrResponseDTO;
import com.Davivienda.DTO.LoginRequestDTO;
import com.Davivienda.model.Usuario;
import com.Davivienda.repository.UsuarioRepository;
import com.Davivienda.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * SERVICIO DE AUTENTICACIÓN (AuthService)
 *
 * Esta clase maneja todo el proceso de autenticación de usuarios:
 *
 * 1. Recibe las credenciales desde el login (email + clave).
 * 2. Usa el AuthenticationManager de Spring Security para validar la contraseña.
 * 3. Carga los detalles del usuario (roles, email, hash) mediante UserDetailsService.
 * 4. Genera un token JWT personalizado usando JwtUtil.
 * 5. Retorna un DTO con el token y datos básicos del usuario.
 *
 * Este servicio trabaja junto con:
 *  - SecurityConfig (configura AuthenticationManager y seguridad HTTP)
 *  - JwtAuthenticationFilter (valida el token en cada request)
 *  - CustomUserDetailsService (carga los usuarios desde la BD)
 *  - JwtUtil (genera y valida tokens JWT)
 *
 * El objetivo es centralizar la lógica del login y mantener el controlador liviano.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

// Maneja internamente el proceso de autenticación de Spring Security
private final AuthenticationManager authenticationManager;

// Servicio que carga las credenciales y roles del usuario
private final UserDetailsService userDetailsService;

// Utilidad que crea y valida los tokens JWT
private final JwtUtil jwtUtil;

// Acceso directo a los datos del usuario para completar la respuesta
private final UsuarioRepository usuarioRepository;

/**
 * Autentica un usuario y genera un JWT válido por 24 horas.
 *
 * @param dto objeto con email y clave enviados desde el login
 * @return DTO con token JWT + datos básicos del usuario autenticado
 */
public JwrResponseDTO login(LoginRequestDTO dto) {
	
	// 1. Crear un objeto de autenticación con las credenciales
	UsernamePasswordAuthenticationToken authToken =
			new UsernamePasswordAuthenticationToken(
					dto.getEmail(),
					dto.getClave()
			);
	
	// 2. Validar el usuario y contraseña
	//    Si las credenciales son inválidas, Spring lanza una excepción automáticamente.
	authenticationManager.authenticate(authToken);
	
	// 3. Cargar detalles del usuario
	//    Incluye roles, username y hash de contraseña.
	UserDetails userDetails =
			userDetailsService.loadUserByUsername(dto.getEmail());
	
	// 4. Generar un token JWT usando el email como "subject"
	String token = jwtUtil.generateToken(userDetails.getUsername());
	
	// 5. Obtener el usuario real desde la BD para completar la respuesta
	Usuario usuario = usuarioRepository.findByEmail(dto.getEmail())
			                  .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	
	// 6. Construir la respuesta
	JwrResponseDTO respuesta = new JwrResponseDTO();
	respuesta.setToken(token);
	respuesta.setEmail(usuario.getEmail());
	respuesta.setNombreCompleto(usuario.getNombreCompleto());
	respuesta.setUsuarioId(usuario.getId());
	
	
	return respuesta;
}
}
