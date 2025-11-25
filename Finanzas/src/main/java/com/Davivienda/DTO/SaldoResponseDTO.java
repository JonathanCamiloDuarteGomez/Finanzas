package com.Davivienda.DTO;

import lombok.Getter;
import lombok.Setter;

// DTO que representa el saldo completo de un usuario
@Getter
@Setter
public class SaldoResponseDTO {

// Total de ingresos sumados
private Double totalIngresos;

// Total de gastos sumados
private Double totalGastos;

// Resultado final: ingresos - gastos
private Double saldoActual;
}

