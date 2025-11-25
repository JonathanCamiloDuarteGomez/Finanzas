package com.Davivienda.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Usuario {

@Id
@GeneratedValue(generator = "UUID")
@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
private UUID id;

@Column(nullable = false, unique = true, length = 255)
private String email;

@Column(nullable = false, length = 255)
private String claveHash;

@Column(name = "nombre_completo")
private String nombreCompleto;

@Column(name = "roles")
private String roles;

@Column(name = "creado_en")
private LocalDateTime creadoEn = LocalDateTime.now();

@Column(name = "actualizado_en")
private LocalDateTime actualizadoEn = LocalDateTime.now();

@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Categoria> categorias = new ArrayList<>();

@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
private List<Transaccion> transacciones = new ArrayList<>();
}

