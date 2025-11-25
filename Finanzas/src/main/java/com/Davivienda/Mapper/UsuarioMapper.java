package com.Davivienda.Mapper;

import com.Davivienda.DTO.UsuarioDTO;
import com.Davivienda.DTO.UsuarioRegistroDTO;
import com.Davivienda.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MAPPER DE USUARIO
 *
 * MapStruct convierte automáticamente Entidades ↔ DTOs.
 * No escribes lógica a mano, MapStruct genera el código por ti.
 */
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

/**
 * Convierte un Usuario (entidad JPA) a UsuarioDTO (objeto que enviamos al frontend).
 */
UsuarioDTO toDTO(Usuario usuario);

/**
 * Convierte un DTO de registro a una entidad Usuario.
 * NOTA: El campo claveHash NO viene aquí todavía,
 *       se asigna en el servicio al encriptar la contraseña.
 */
//@Mapping(target = "email", source = "email")
@Mapping(target = "id", ignore = true)
@Mapping(target = "claveHash", ignore = true)
@Mapping(target = "roles", ignore = true)
@Mapping(target = "categorias", ignore = true)
@Mapping(target = "transacciones", ignore = true)
@Mapping(target = "creadoEn", expression = "java(java.time.LocalDateTime.now())")
@Mapping(target = "actualizadoEn", expression = "java(java.time.LocalDateTime.now())")
Usuario toEntity(UsuarioRegistroDTO dto);
}

