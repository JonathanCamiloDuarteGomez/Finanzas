package com.Davivienda.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "transacciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

@Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
private UUID id;

@Column(nullable = false)
private LocalDate fecha;

@Column(nullable = false)
private Double monto;

@Enumerated(EnumType.STRING)
@Column(nullable = false)
private TipoTransaccion tipo;

@Column(columnDefinition = "text")
private String descripcion;

@Column(columnDefinition = "jsonb")
private String etiquetas;

@Column(name = "creado_en")
private LocalDateTime creadoEn = LocalDateTime.now();

@Column(name = "actualizado_en")
private LocalDateTime actualizadoEn = LocalDateTime.now();

// RELACIONES

@ManyToOne
@JoinColumn(name = "usuario_id", nullable = false)
private Usuario usuario;

@ManyToOne
@JoinColumn(name = "categoria_id")
private Categoria categoria;
}

