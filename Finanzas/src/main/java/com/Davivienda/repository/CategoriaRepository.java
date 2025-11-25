package com.Davivienda.repository;

import com.Davivienda.model.Categoria;
import com.Davivienda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;


public interface CategoriaRepository extends JpaRepository<Categoria, UUID> {

List<Categoria> findByUsuario(Usuario usuario);

boolean existsByUsuarioAndNombre(Usuario usuario, String nombre);
}
