package com.Davivienda.Mapper;

import com.Davivienda.DTO.CategoriaDTO;
import com.Davivienda.DTO.CategoriaRequestDTO;
import com.Davivienda.model.Categoria;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T10:22:26-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class CategoriaMapperImpl implements CategoriaMapper {

    @Override
    public CategoriaDTO toDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDTO categoriaDTO = new CategoriaDTO();

        categoriaDTO.setId( categoria.getId() );
        categoriaDTO.setNombre( categoria.getNombre() );
        categoriaDTO.setDescripcion( categoria.getDescripcion() );
        categoriaDTO.setTipo( categoria.getTipo() );
        categoriaDTO.setColor( categoria.getColor() );

        return categoriaDTO;
    }

    @Override
    public Categoria toEntity(CategoriaRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Categoria.CategoriaBuilder categoria = Categoria.builder();

        categoria.nombre( dto.getNombre() );
        categoria.tipo( dto.getTipo() );
        categoria.color( dto.getColor() );
        categoria.descripcion( dto.getDescripcion() );

        categoria.creadoEn( java.time.LocalDateTime.now() );
        categoria.actualizadoEn( java.time.LocalDateTime.now() );

        return categoria.build();
    }
}
