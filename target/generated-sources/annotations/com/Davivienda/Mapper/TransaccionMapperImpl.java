package com.Davivienda.Mapper;

import com.Davivienda.DTO.TransaccionDTO;
import com.Davivienda.DTO.TransaccionRequestDTO;
import com.Davivienda.model.Transaccion;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T10:22:27-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class TransaccionMapperImpl implements TransaccionMapper {

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Override
    public TransaccionDTO toDTO(Transaccion transaccion) {
        if ( transaccion == null ) {
            return null;
        }

        TransaccionDTO transaccionDTO = new TransaccionDTO();

        transaccionDTO.setId( transaccion.getId() );
        transaccionDTO.setFecha( transaccion.getFecha() );
        transaccionDTO.setMonto( transaccion.getMonto() );
        transaccionDTO.setTipo( transaccion.getTipo() );
        transaccionDTO.setDescripcion( transaccion.getDescripcion() );
        transaccionDTO.setEtiquetas( transaccion.getEtiquetas() );
        transaccionDTO.setCategoria( categoriaMapper.toDTO( transaccion.getCategoria() ) );

        afterMappingToDTO( transaccionDTO, transaccion );

        return transaccionDTO;
    }

    @Override
    public Transaccion toEntity(TransaccionRequestDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Transaccion.TransaccionBuilder transaccion = Transaccion.builder();

        transaccion.fecha( dto.getFecha() );
        transaccion.monto( dto.getMonto() );
        transaccion.tipo( dto.getTipo() );
        transaccion.descripcion( dto.getDescripcion() );
        transaccion.etiquetas( dto.getEtiquetas() );

        transaccion.creadoEn( java.time.LocalDateTime.now() );
        transaccion.actualizadoEn( java.time.LocalDateTime.now() );

        return transaccion.build();
    }
}
