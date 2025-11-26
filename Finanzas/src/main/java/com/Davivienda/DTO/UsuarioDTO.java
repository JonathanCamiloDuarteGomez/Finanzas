package com.Davivienda.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UsuarioDTO {//para respuesta de usuario

private UUID id;
private String email;
private String nombreCompleto;
private List<String> roles;
}