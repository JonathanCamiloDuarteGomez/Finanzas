package com.Davivienda.Controller;

import com.Davivienda.DTO.SaldoResponseDTO;
import com.Davivienda.DTO.UsuarioActualizarDTO;
import com.Davivienda.DTO.UsuarioDTO;
import com.Davivienda.DTO.UsuarioRegistroDTO;
import com.Davivienda.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * CONTROLADOR DE USUARIO (Opción B)
 * - Registro
 * - Obtener usuario
 * - Actualizar perfil
 * - Obtener saldo
 */
@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Gestión de usuarios y perfil")
public class UsuarioController {

private final UsuarioService usuarioService;

/**
 * Registrar un usuario nuevo
 */
@Operation(summary = "Registrar usuario")
@PostMapping("/registro")
public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioRegistroDTO dto) {
	return ResponseEntity.ok(usuarioService.registrar(dto));
}

/**
 * Obtener usuario por ID
 */
@Operation(summary = "Obtener usuario por ID")
@GetMapping("/{id}")
public ResponseEntity<UsuarioDTO> obtener(@PathVariable UUID id) {
	return ResponseEntity.ok(usuarioService.obtenerPorId(id));
}

/**
 * Actualizar datos del perfil del usuario
 */
@Operation(summary = "Actualizar perfil del usuario")
@PutMapping("/{id}")
public ResponseEntity<UsuarioDTO> actualizar(
		@PathVariable UUID id,
		@RequestBody UsuarioActualizarDTO dto
) {
	return ResponseEntity.ok(usuarioService.actualizarPerfil(id, dto));
}

/**
 * Obtener el saldo completo de un usuario
 */
@Operation(summary = "Obtener saldo del usuario")
@GetMapping("/{usuarioId}/saldo")
public ResponseEntity<SaldoResponseDTO> obtenerSaldo(@PathVariable UUID usuarioId) {
	return ResponseEntity.ok(usuarioService.obtenerSaldo(usuarioId));
}
}
