package com.Davivienda.config.security;


import com.Davivienda.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * CONFIGURACIÓN PRINCIPAL DE SEGURIDAD CON JWT
 *
 * Esta clase controla:
 *  - Qué endpoints son públicos (registro, login, swagger)
 *  - Qué endpoints requieren autenticación (todos los demás)
 *  - Cómo se maneja el token JWT en cada request
 *  - Deshabilita sesiones y CSRF (porque usamos JWT)
 *
 * Spring Security funciona con una cadena de filtros (Filter Chain).
 * Aquí añadimos nuestro JwtAuthenticationFilter para validar tokens
 * ANTES de que Spring procese la autenticación.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

// Filtro de validación de JWT
private final JwtAuthenticationFilter jwtAuthenticationFilter;

// Servicio que carga usuarios desde la base de datos
private final CustomUserDetailsService customUserDetailsService;

/**
 * BCrypt PasswordEncoder — algoritmo recomendado por Spring Security.
 * Se usa para encriptar contraseñas y compararlas de forma segura.
 */
@Bean
public PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder();
}

/**
 * AuthenticationManager — se requiere para el login.
 * Spring lo crea automáticamente a partir del AuthenticationConfiguration.
 */
@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	return config.getAuthenticationManager();
}

/**
 * SecurityFilterChain — define TODA la configuración de seguridad.
 *
 * Aquí definimos:
 *  - CSRF deshabilitado
 *  - Sesión stateless (JWT)
 *  - Rutas públicas vs protegidas
 *  - Filtro JWT antes del filtro estándar de autenticación
 */
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	
	http
			// 🔒 CSRF no se usa con JWT
			.csrf(csrf -> csrf.disable())
			
			// ✔ Sin sesiones en servidor — solo JWT
			.sessionManagement(session ->
					                   session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			
			// ✔ Definir rutas públicas y protegidas
			.authorizeHttpRequests(auth -> auth
					                               
					                               /** RUTAS PÚBLICAS */
					                               .requestMatchers(
							                               "/api/usuarios/registro",
							                               "/auth/login",
							                               "/v3/api-docs/**",
							                               "/swagger-ui/**",
							                               "/swagger-ui.html"
					                               ).permitAll()
					                               
					                               
					                               /** TODAS LAS DEMÁS RUTAS REQUIEREN TOKEN */
					                               .anyRequest().authenticated()
			)
			
			// ✔ Indicamos cuál es nuestro servicio de usuarios
			.userDetailsService(customUserDetailsService)
			
			// ✔ Nuestro filtro JWT debe ir ANTES de UsernamePasswordAuthenticationFilter
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	
	return http.build();
}
}
