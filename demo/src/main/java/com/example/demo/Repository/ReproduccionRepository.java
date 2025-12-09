package com.example.demo.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.ReproduccionEntity;

public interface ReproduccionRepository extends JpaRepository<ReproduccionEntity, Integer> {
    public ArrayList<ReproduccionEntity> findAll();

    @Query("SELECT r.contenido.id, COUNT(r) " +
           "FROM ReproduccionEntity r " +
           "GROUP BY r.contenido.id " +
           "HAVING COUNT(r) > :n")
    ArrayList<Object[]> obtenerContenidosConMasDeNReproducciones(@Param("n") int n);
 
    ArrayList<ReproduccionEntity> findByUsuarioId(int usuarioId);

    @Query("SELECT AVG(r.calificacion) FROM ReproduccionEntity r WHERE r.contenido.id = :contenidoId")
    Double promedioCalificacion(@Param("contenidoId") int contenidoId);

    ArrayList<ReproduccionEntity> findByFechaHora(LocalDateTime fechaHora);
}
