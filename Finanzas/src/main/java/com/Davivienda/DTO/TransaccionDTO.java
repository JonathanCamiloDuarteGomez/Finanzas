package com.Davivienda.DTO;

import com.Davivienda.model.TipoTransaccion;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class TransaccionDTO {
private UUID id;
private LocalDate fecha;
private Double monto;
private TipoTransaccion tipo;
private String descripcion;
private String etiquetas;

private CategoriaDTO categoria;
}
