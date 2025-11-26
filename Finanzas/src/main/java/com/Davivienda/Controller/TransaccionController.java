package com.Davivienda.Controller;


import com.Davivienda.DTO.TransaccionDTO;
import com.Davivienda.DTO.TransaccionRequestDTO;
import com.Davivienda.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/transacciones")
@Tag(name = "4. Transacciones", description = "CRUD de transacciones del usuario")
public class TransaccionController {

private final TransaccionService transaccionService;

public TransaccionController(TransaccionService transaccionService) {
	this.transaccionService = transaccionService;
}

// CREAR
@Operation(summary = "Crear transacción")
@PostMapping("/usuario/{usuarioId}")
public ResponseEntity<TransaccionDTO> crear(
		@PathVariable UUID usuarioId,
		@RequestBody TransaccionRequestDTO dto
) {
	return ResponseEntity.ok(transaccionService.crear(usuarioId, dto));
}

// LISTAR POR USUARIO
@Operation(summary = "Listar transacciones por usuario")
@GetMapping("/usuario/{usuarioId}")
public ResponseEntity<List<TransaccionDTO>> listarPorUsuario(@PathVariable UUID usuarioId) {
	return ResponseEntity.ok(transaccionService.listarPorUsuario(usuarioId));
}

// OBTENER POR ID
@Operation(summary = "Obtener transacción por ID")
@GetMapping("/{id}")
public ResponseEntity<TransaccionDTO> obtenerPorId(@PathVariable UUID id) {
	return ResponseEntity.ok(transaccionService.obtenerPorId(id));
}

// ACTUALIZAR
@Operation(summary = "Actualizar transacción")
@PutMapping("/{id}")
public ResponseEntity<TransaccionDTO> actualizar(
		@PathVariable UUID id,
		@RequestBody TransaccionRequestDTO dto
) {
	return ResponseEntity.ok(transaccionService.actualizar(id, dto));
}

// ELIMINAR
@Operation(summary = "Eliminar transacción")
@DeleteMapping("/{id}")
public ResponseEntity<Void> eliminar(@PathVariable UUID id) {
	transaccionService.eliminar(id);
	return ResponseEntity.noContent().build();
}
}


