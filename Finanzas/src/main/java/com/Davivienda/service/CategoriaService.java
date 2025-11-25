package com.Davivienda.service;


import com.Davivienda.DTO.CategoriaDTO;
import com.Davivienda.DTO.CategoriaRequestDTO;
import com.Davivienda.Mapper.CategoriaMapper;
import com.Davivienda.model.Categoria;
import com.Davivienda.model.Usuario;
import com.Davivienda.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Servicio de manejo de categorías.
 * Cada categoría pertenece a un usuario.
 */
@Service
@RequiredArgsConstructor
public class CategoriaService {

private final CategoriaRepository categoriaRepository;
private final CategoriaMapper categoriaMapper;
private final UsuarioService usuarioService;

/**
 * Crea una categoría asociada a un usuario.
 */
public CategoriaDTO crear(UUID usuarioId, CategoriaRequestDTO dto) {
	System.out.println("Datos recibidos: " + dto.getNombre() + ", " + dto.getTipo() + ", " + dto.getColor());
	
	Usuario usuario = usuarioService.obtenerEntidad(usuarioId);
	Categoria categoria = categoriaMapper.toEntity(dto);
	System.out.println("Categoria mapeada: " + categoria.getNombre() + ", " + categoria.getTipo());
	
	categoria.setUsuario(usuario);
	Categoria guardada = categoriaRepository.save(categoria);
	
	return categoriaMapper.toDTO(guardada);
}

/**
 * Lista todas las categorías de un usuario.
 */
public List<CategoriaDTO> listarPorUsuario(UUID usuarioId) {
	Usuario usuario = usuarioService.obtenerEntidad(usuarioId);
	return categoriaRepository.findByUsuario(usuario)
			       .stream()
			       .map(categoriaMapper::toDTO)
			       .toList();
}

// ======================================================
// OBTENER CATEGORÍA POR ID
// ======================================================
public CategoriaDTO obtenerPorId(UUID categoriaId) {
	Categoria categoria = categoriaRepository.findById(categoriaId)
			                      .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
	
	return categoriaMapper.toDTO(categoria);
}

// ======================================================
// ACTUALIZAR CATEGORÍA
// ======================================================
public CategoriaDTO actualizar(UUID categoriaId, CategoriaRequestDTO dto) {
	
	Categoria categoria = categoriaRepository.findById(categoriaId)
			                      .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
	
	categoria.setNombre(dto.getNombre());
	categoria.setDescripcion(dto.getDescripcion());
	categoria.setColor(dto.getColor());
	categoria.setTipo(dto.getTipo());
	categoria.setActualizadoEn(java.time.LocalDateTime.now());
	
	Categoria updated = categoriaRepository.save(categoria);
	
	return categoriaMapper.toDTO(updated);
}

// ======================================================
// ELIMINAR CATEGORÍA
// ======================================================
public void eliminar(UUID categoriaId) {
	if (!categoriaRepository.existsById(categoriaId)) {
		throw new RuntimeException("Categoría no encontrada");
	}
	categoriaRepository.deleteById(categoriaId);
}


}

