package com.Davivienda.Mapper;

import com.Davivienda.DTO.UsuarioDTO;
import com.Davivienda.DTO.UsuarioRegistroDTO;
import com.Davivienda.model.Usuario;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-25T10:22:27-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO toDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setEmail( usuario.getEmail() );
        usuarioDTO.setNombreCompleto( usuario.getNombreCompleto() );
        usuarioDTO.setRoles( usuario.getRoles() );

        return usuarioDTO;
    }

    @Override
    public Usuario toEntity(UsuarioRegistroDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Usuario.UsuarioBuilder usuario = Usuario.builder();

        usuario.email( dto.getEmail() );
        usuario.nombreCompleto( dto.getNombreCompleto() );

        usuario.creadoEn( java.time.LocalDateTime.now() );
        usuario.actualizadoEn( java.time.LocalDateTime.now() );

        return usuario.build();
    }
}
