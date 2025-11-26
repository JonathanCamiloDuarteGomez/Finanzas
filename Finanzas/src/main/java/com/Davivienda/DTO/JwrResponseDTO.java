package com.Davivienda.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class JwrResponseDTO {
private String token;
private String tipo = "Bearer";

private String email;
private String nombreCompleto;
private UUID usuarioId;

}
