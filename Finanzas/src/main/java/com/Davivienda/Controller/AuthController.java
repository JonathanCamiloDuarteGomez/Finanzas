package com.Davivienda.Controller;


import com.Davivienda.DTO.JwrResponseDTO;
import com.Davivienda.DTO.LoginRequestDTO;
import com.Davivienda.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * CONTROLADOR DE AUTENTICACIÓN (AuthController)
 *
 * Este controlador expone los endpoints relacionados con la autenticación
 * de usuarios en el sistema. Actualmente incluye:
 *
 *  ✔ /auth/login  →  Validación de credenciales y generación de token JWT
 *
 * El flujo es el siguiente:
 *  1. El usuario envía email + clave.
 *  2. El controlador delega en AuthService la verificación de credenciales.
 *  3. Si son correctas, se genera un JWT firmado.
 *  4. Se responde al cliente con el token y datos básicos del usuario.
 *
 * El token generado debe enviarse luego en todas las peticiones protegidas:
 *
 *     Authorization: Bearer <token>
 *
 * Este controlador mantiene la lógica mínima posible,
 * delegando todo el trabajo real a AuthService (buenas prácticas REST).
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

@Tag(name = "2. Autenticación", description = "Iniciar sesión y obtener un token JWT")
public class AuthController {

// Servicio encargado del proceso de autenticación
private final AuthService authService;

/**
 * Endpoint para iniciar sesión en el sistema.
 *
 * Recibe un JSON con email y clave, valida el usuario y,
 * si es correcto, retorna un token JWT firmado.
 *
 * @param dto objeto LoginRequestDTO con email y contraseña
 * @return un JwrResponseDTO con el JWT y datos del usuario
 */
@Operation(summary = "Iniciar sesión y obtener JWT")
@PostMapping("/login")
public ResponseEntity<JwrResponseDTO> login(@RequestBody LoginRequestDTO dto) {
	return ResponseEntity.ok(authService.login(dto));
}
}
