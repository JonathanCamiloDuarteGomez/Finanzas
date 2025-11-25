package com.Davivienda.Mapper;




import com.Davivienda.DTO.CategoriaDTO;
import com.Davivienda.DTO.CategoriaRequestDTO;
import com.Davivienda.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * MAPPER DE CATEGORÍA
 *
 * Se encarga de transformar:
 *   - Entidad → DTO
 *   - DTO → Entidad
 */
@Mapper(componentModel = "spring")
public interface CategoriaMapper {

CategoriaDTO toDTO(Categoria categoria);

/**
 * Convierte un DTO de request a una Categoría.
 * El usuario se asigna en el servicio.
 */
@Mapping(target = "id", ignore = true)
@Mapping(target = "usuario", ignore = true)
@Mapping(target = "transacciones", ignore = true)
@Mapping(target = "creadoEn", expression = "java(java.time.LocalDateTime.now())")
@Mapping(target = "actualizadoEn", expression = "java(java.time.LocalDateTime.now())")
@Mapping(source = "nombre", target = "nombre")
@Mapping(source = "tipo", target = "tipo")
@Mapping(source = "color", target = "color")
Categoria toEntity(CategoriaRequestDTO dto);
}
