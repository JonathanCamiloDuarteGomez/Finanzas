package com.Davivienda.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {//DTO del Login / JWT

@NotBlank(message = "El correo es obligatorio.")
private String correo;

@NotBlank(message = "La contraseña es obligatoria.")
private String clave;
}
