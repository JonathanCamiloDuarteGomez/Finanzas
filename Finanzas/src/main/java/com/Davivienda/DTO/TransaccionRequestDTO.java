package com.Davivienda.DTO;

import com.Davivienda.model.TipoTransaccion;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
public class TransaccionRequestDTO {//para crear transacción

@NotNull(message = "La fecha es obligatoria.")
private LocalDate fecha;

@NotNull(message = "El monto es obligatorio.")
private Double monto;

@NotNull(message = "Debe especificar el tipo de transacción.")
private TipoTransaccion tipo;

private String descripcion;

private String etiquetas;

private UUID categoriaId; // FK
}
