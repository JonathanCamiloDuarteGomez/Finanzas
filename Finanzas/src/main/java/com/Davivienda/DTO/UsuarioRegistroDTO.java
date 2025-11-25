package com.Davivienda.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsuarioRegistroDTO {//para crear un usuario (registro)

@Email(message = "El correo no es válido.")
@NotBlank(message = "El correo es obligatorio.")
private String email ;

@NotBlank(message = "La contraseña es obligatoria.")
private String clave;

private String nombreCompleto;
}
