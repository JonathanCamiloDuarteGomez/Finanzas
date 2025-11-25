package com.Davivienda.service;

import com.Davivienda.DTO.SaldoResponseDTO;
import com.Davivienda.DTO.UsuarioActualizarDTO;
import com.Davivienda.DTO.UsuarioDTO;
import com.Davivienda.DTO.UsuarioRegistroDTO;
import com.Davivienda.Mapper.UsuarioMapper;
import com.Davivienda.model.Usuario;
import com.Davivienda.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Davivienda.repository.TransaccionRepository;

import java.util.UUID;


/**
 * Servicio encargado de gestionar la lógica de negocio de los usuarios.
 * Maneja registro, consulta y actualización.
 */
@Service
@RequiredArgsConstructor
public class UsuarioService {

private final UsuarioRepository usuarioRepository;
private final UsuarioMapper usuarioMapper;
private final PasswordEncoder passwordEncoder;
private final TransaccionRepository transaccionRepository;
/**
 * Registra un nuevo usuario en la plataforma.
 */
public UsuarioDTO registrar(UsuarioRegistroDTO dto) {
	
	System.out.println("Registrando usuario con email: " + dto.getEmail());
	System.out.println("Nombre completo: " + dto.getNombreCompleto());
	// Validar si ya existe un usuario con ese email
	if (usuarioRepository.existsByEmail(dto.getEmail())) {
		throw new RuntimeException("El correo ya está registrado.");
	}
	
	// Convertimos DTO → Entidad
	Usuario usuario = usuarioMapper.toEntity(dto);
	
	// Encriptamos contraseña
	usuario.setClaveHash(passwordEncoder.encode(dto.getClave()));
	
	// Guardamos en BD
	Usuario guardado = usuarioRepository.save(usuario);
	
	// Retornamos DTO
	return usuarioMapper.toDTO(guardado);
}

/**
 * Obtiene un usuario por ID.
 */
public UsuarioDTO obtenerPorId(UUID id) {
	Usuario usuario = usuarioRepository.findById(id)
			                  .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
	
	return usuarioMapper.toDTO(usuario);
}

/**
 * Obtiene la entidad de usuario (útil para otras capas).
 */
public Usuario obtenerEntidad(UUID id) {
	return usuarioRepository.findById(id)
			       .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
}

public SaldoResponseDTO obtenerSaldo(UUID usuarioId) {
	
	// Validar que el usuario exista; si no, lanzar error controlado
	Usuario usuario = usuarioRepository.findById(usuarioId)
			                  .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	
	// Obtener suma de ingresos desde la BD
	Double ingresos = transaccionRepository.totalIngresos(usuarioId);
	
	// Obtener suma de gastos desde la BD
	Double gastos = transaccionRepository.totalGastos(usuarioId);
	
	// Crear el DTO para enviar al cliente
	SaldoResponseDTO dto = new SaldoResponseDTO();
	dto.setTotalIngresos(ingresos);
	dto.setTotalGastos(gastos);
	
	// Calcular saldo final
	dto.setSaldoActual(ingresos - gastos);
	
	return dto;
}

public UsuarioDTO actualizarPerfil(UUID id, UsuarioActualizarDTO dto) {
	
	Usuario usuario = usuarioRepository.findById(id)
			                  .orElseThrow(() -> new RuntimeException("Usuario no encontrado."));
	
	// Actualización simple
	usuario.setNombreCompleto(dto.getNombreCompleto());
	
	usuario.setActualizadoEn(java.time.LocalDateTime.now());
	
	Usuario updated = usuarioRepository.save(usuario);
	
	return usuarioMapper.toDTO(updated);
}

}

