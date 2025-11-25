package com.Davivienda.repository;

import com.Davivienda.model.Categoria;
import com.Davivienda.model.TipoTransaccion;
import com.Davivienda.model.Transaccion;
import com.Davivienda.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface TransaccionRepository extends JpaRepository<Transaccion, UUID> {

List<Transaccion> findByUsuario(Usuario usuario);

List<Transaccion> findByUsuarioId(UUID usuarioId);

List<Transaccion> findByUsuarioAndFechaBetween(Usuario usuario, Date inicio, Date fin);

List<Transaccion> findByUsuarioAndCategoria(Usuario usuario, Categoria categoria);

List<Transaccion> findByUsuarioAndTipo(Usuario usuario, TipoTransaccion tipo);

// Suma todos los montos donde el tipo es INGRESO para un usuario específico
@Query("SELECT COALESCE(SUM(t.monto), 0) FROM Transaccion t WHERE t.usuario.id = :usuarioId AND t.tipo = 'INGRESO'")
Double totalIngresos(UUID usuarioId);

// Suma todos los montos donde el tipo es GASTO para un usuario específico
@Query("SELECT COALESCE(SUM(t.monto), 0) FROM Transaccion t WHERE t.usuario.id = :usuarioId AND t.tipo = 'GASTO'")
Double totalGastos(UUID usuarioId);

}
