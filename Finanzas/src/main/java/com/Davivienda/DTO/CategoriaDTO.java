package com.Davivienda.DTO;

import com.Davivienda.model.TipoTransaccion;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {//para respuesta de categoría
private UUID id;
private String nombre;

private  String descripcion;

private TipoTransaccion tipo;
private String color;
}
