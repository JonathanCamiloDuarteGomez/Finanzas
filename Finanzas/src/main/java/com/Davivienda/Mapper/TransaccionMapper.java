package com.Davivienda.Mapper;


import com.Davivienda.DTO.TransaccionDTO;
import com.Davivienda.DTO.TransaccionRequestDTO;
import com.Davivienda.model.Transaccion;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CategoriaMapper.class})
public interface TransaccionMapper {

@Mapping(target = "id", source = "id")
@Mapping(target = "fecha", source = "fecha")
@Mapping(target = "monto", source = "monto")
@Mapping(target = "tipo", source = "tipo")
@Mapping(target = "descripcion", source = "descripcion")
@Mapping(target = "etiquetas", source = "etiquetas")
@Mapping(target = "categoria", source = "categoria")
TransaccionDTO toDTO(Transaccion transaccion);

@Mapping(target = "id", ignore = true)
@Mapping(target = "usuario", ignore = true)
@Mapping(target = "categoria", ignore = true)
@Mapping(target = "fecha", source = "fecha")
@Mapping(target = "monto", source = "monto")
@Mapping(target = "tipo", source = "tipo")
@Mapping(target = "descripcion", source = "descripcion")
@Mapping(target = "etiquetas", source = "etiquetas")
@Mapping(target = "creadoEn", expression = "java(java.time.LocalDateTime.now())")
@Mapping(target = "actualizadoEn", expression = "java(java.time.LocalDateTime.now())")
Transaccion toEntity(TransaccionRequestDTO dto);

@AfterMapping
default void afterMapping(@MappingTarget Transaccion target, TransaccionRequestDTO source) {
	System.out.println("Mapeo DTO → Entidad:");
	System.out.println("DTO: " + source);
	System.out.println("Entidad resultante: " + target);
}

@AfterMapping
default void afterMappingToDTO(@MappingTarget TransaccionDTO target, Transaccion source) {
	System.out.println("Mapeo Entidad → DTO:");
	System.out.println("Entidad: " + source);
	System.out.println("DTO resultante: " + target);
}
}