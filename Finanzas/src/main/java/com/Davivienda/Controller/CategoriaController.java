package com.Davivienda.Controller;


import com.Davivienda.DTO.CategoriaDTO;
import com.Davivienda.DTO.CategoriaRequestDTO;
import com.Davivienda.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * CONTROLADOR DE CATEGORÍAS
 * Gestiona la creación y consulta de categorías por usuario.
 */

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor

@Tag(name = "3. Categorías", description = "CRUD de categorías del usuario")

public class CategoriaController {

private final CategoriaService categoriaService;

/**
 * Crear una categoría asociada a un usuario
 */
@Operation(summary = "Crear categoría")
@PostMapping("/usuario/{usuarioId}")
public ResponseEntity<CategoriaDTO> crear(
		@PathVariable UUID usuarioId,
		@RequestBody CategoriaRequestDTO dto
) {
	return ResponseEntity.ok(categoriaService.crear(usuarioId, dto));
}

/**
 * Listar categorías por usuario
 */
@Operation(summary = "Listar categorías por usuario")
@GetMapping("/usuario/{usuarioId}")
public ResponseEntity<List<CategoriaDTO>> listar(@PathVariable UUID usuarioId) {
	return ResponseEntity.ok(categoriaService.listarPorUsuario(usuarioId));
}
// OBTENER CATEGORÍA POR ID

@Operation(summary = "Obtener categoría por ID")
@GetMapping("/{categoriaId}")
public ResponseEntity<CategoriaDTO> obtenerPorId(@PathVariable UUID categoriaId) {
	return ResponseEntity.ok(categoriaService.obtenerPorId(categoriaId));
}

// ACTUALIZAR CATEGORÍA

@Operation(summary = "Actualizar categoría")
@PutMapping("/{categoriaId}")
public ResponseEntity<CategoriaDTO> actualizar(
		@PathVariable UUID categoriaId,
		@RequestBody CategoriaRequestDTO dto
) {
	return ResponseEntity.ok(categoriaService.actualizar(categoriaId, dto));
}

// ELIMINAR CATEGORÍA

@Operation(summary = "Eliminar categoría")
@DeleteMapping("/{categoriaId}")
public ResponseEntity<Void> eliminar(@PathVariable UUID categoriaId) {
	categoriaService.eliminar(categoriaId);
	return ResponseEntity.noContent().build();
}

}
