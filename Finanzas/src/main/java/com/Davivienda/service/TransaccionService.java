package com.Davivienda.service;

import com.Davivienda.DTO.TransaccionDTO;
import com.Davivienda.DTO.TransaccionRequestDTO;
import com.Davivienda.Mapper.TransaccionMapper;
import com.Davivienda.model.Categoria;
import com.Davivienda.model.Transaccion;
import com.Davivienda.model.Usuario;
import com.Davivienda.repository.CategoriaRepository;
import com.Davivienda.repository.TransaccionRepository;
import com.Davivienda.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransaccionService {

private final TransaccionRepository transaccionRepository;
private final UsuarioRepository usuarioRepository;
private final CategoriaRepository categoriaRepository;
private final TransaccionMapper transaccionMapper;

public TransaccionService(
		TransaccionRepository transaccionRepository,
		UsuarioRepository usuarioRepository,
		CategoriaRepository categoriaRepository,
		TransaccionMapper transaccionMapper
) {
	this.transaccionRepository = transaccionRepository;
	this.usuarioRepository = usuarioRepository;
	this.categoriaRepository = categoriaRepository;
	this.transaccionMapper = transaccionMapper;
}

// ======================================================
// CREAR TRANSACCIÓN
// ======================================================
public TransaccionDTO crear(UUID usuarioId, TransaccionRequestDTO dto) {
	
	Usuario usuario = usuarioRepository.findById(usuarioId)
			                  .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	
	Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
			                      .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
	
	Transaccion transaccion = transaccionMapper.toEntity(dto);
	transaccion.setUsuario(usuario);
	transaccion.setCategoria(categoria);
	
	// Guardar
	Transaccion saved = transaccionRepository.save(transaccion);
	
	// Recargar desde BD para que el mapper reciba info completa
	Transaccion loaded = transaccionRepository.findById(saved.getId())
			                     .orElseThrow();
	
	return transaccionMapper.toDTO(loaded);
}

// ======================================================
// LISTAR TRANSACCIONES POR USUARIO
// ======================================================
public List<TransaccionDTO> listarPorUsuario(UUID usuarioId) {
	return transaccionRepository.findByUsuarioId(usuarioId)
			       .stream()
			       .map(transaccionMapper::toDTO)
			       .toList();
}

// ======================================================
// OBTENER POR ID
// ======================================================
public TransaccionDTO obtenerPorId(UUID id) {
	Transaccion t = transaccionRepository.findById(id)
			                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
	
	return transaccionMapper.toDTO(t);
}

// ======================================================
// ACTUALIZAR
// ======================================================
public TransaccionDTO actualizar(UUID id, TransaccionRequestDTO dto) {
	
	Transaccion transaccion = transaccionRepository.findById(id)
			                          .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));
	
	Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
			                      .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
	
	// Actualizar datos simples
	transaccion.setFecha(dto.getFecha());
	transaccion.setMonto(dto.getMonto());
	transaccion.setTipo(dto.getTipo());
	transaccion.setDescripcion(dto.getDescripcion());
	transaccion.setEtiquetas(dto.getEtiquetas());
	transaccion.setCategoria(categoria);
	transaccion.setActualizadoEn(java.time.LocalDateTime.now());
	
	Transaccion updated = transaccionRepository.save(transaccion);
	
	return transaccionMapper.toDTO(updated);
}

// ======================================================
// ELIMINAR
// ======================================================
public void eliminar(UUID id) {
	if (!transaccionRepository.existsById(id)) {
		throw new RuntimeException("Transacción no encontrada");
	}
	transaccionRepository.deleteById(id);
}
}
