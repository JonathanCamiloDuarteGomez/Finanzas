
package com.Davivienda.config.security;

import com.Davivienda.utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
		                                                     import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro de autenticación basado en JWT.
 *
 * Este filtro se ejecuta UNA VEZ por cada request (thanks to OncePerRequestFilter).
 * Su función principal es:
 *
 * 1. Leer el header Authorization.
 * 2. Extraer y validar el token JWT.
 * 3. Obtener el usuario asociado al token.
 * 4. Construir la autenticación de Spring Security.
 * 5. Registrar al usuario como autenticado en el SecurityContext.
 *
 * Si el token no existe o es inválido, simplemente deja pasar la request
 * sin autenticar (las reglas de SecurityConfig decidirán si se permite o no).
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

// Utilidad para generar/validar tokens JWT
private final JwtUtil jwtUtil;

// Servicio que obtiene UserDetails desde la base de datos
private final UserDetailsService userDetailsService;

/**
 * Método principal del filtro JWT.
 * Intercepta TODAS las solicitudes entrantes.
 */
@Override
protected void doFilterInternal(
		HttpServletRequest request,
		HttpServletResponse response,
		FilterChain filterChain
) throws ServletException, IOException {
	
	// 1. Obtener el header Authorization
	String authHeader = request.getHeader("Authorization");
	
	String username = null;
	String token = null;
	
	/**
	 * 2. Verificar si el header Authorization existe y
	 *    si sigue el formato estándar:
	 *
	 *    Authorization: Bearer <token>
	 */
	if (authHeader != null && authHeader.startsWith("Bearer ")) {
		
		// Extraer únicamente el token sin el prefijo "Bearer "
		token = authHeader.substring(7);
		
		// 3. Validar token
		if (jwtUtil.isTokenValid(token)) {
			// Extraer username (email) desde el token
			username = jwtUtil.getUsernameFromToken(token);
		}
	}
	
	/**
	 * 4. Si tenemos un usuario extraído del token
	 *    y todavía no existe autenticación previa en esta request:
	 */
	if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		
		// 5. Obtener UserDetails desde BD
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		
		/**
		 * 6. Crear un objeto de autenticación válido para Spring Security.
		 *    Este objeto contiene:
		 *    - Credenciales (null porque ya están validadas por JWT)
		 *    - Roles (autoridades)
		 */
		UsernamePasswordAuthenticationToken authToken =
				new UsernamePasswordAuthenticationToken(
						userDetails,
						null,
						userDetails.getAuthorities()
				);
		
		// Añadir información extra de la request (como IP, sesión, etc.)
		authToken.setDetails(
				new WebAuthenticationDetailsSource().buildDetails(request)
		);
		
		// 7. Registrar al usuario como autenticado en el contexto de seguridad
		SecurityContextHolder.getContext().setAuthentication(authToken);
	}
	
	// 8. Continuar con el resto del filtro/cadena
	filterChain.doFilter(request, response);
}
}
