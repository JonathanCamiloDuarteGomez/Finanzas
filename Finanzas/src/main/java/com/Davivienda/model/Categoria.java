package com.Davivienda.model;


import com.Davivienda.utils.TipoTransaccionConverter;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Categoria {

@Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
private UUID id;

@Column(nullable = false, length = 100)
private String nombre;

@Column(columnDefinition = "text")
private String descripcion;


@Enumerated(EnumType.STRING)
private TipoTransaccion tipo;

private String color;

@Column(name = "creado_en")
private LocalDateTime creadoEn = LocalDateTime.now();

@Column(name = "actualizado_en")
private LocalDateTime actualizadoEn = LocalDateTime.now();

// RELACIONES
@ManyToOne
@JoinColumn(name = "usuario_id", nullable = false)
private Usuario usuario;

@OneToMany(mappedBy = "categoria", cascade = CascadeType.PERSIST)
private List<Transaccion> transacciones = new ArrayList<>();
}

