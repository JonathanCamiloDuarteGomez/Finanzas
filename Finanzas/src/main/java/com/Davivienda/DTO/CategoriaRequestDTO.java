package com.Davivienda.DTO;

import com.Davivienda.model.TipoTransaccion;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaRequestDTO {  // para crear/actualizar categoría

@NotBlank(message = "El nombre de la categoría es obligatorio.")
private String nombre;

private String descripcion;

@NotNull(message = "El tipo de transacción es obligatorio.")
private TipoTransaccion tipo;

@NotBlank(message = "El color es obligatorio.")
private String color;
}