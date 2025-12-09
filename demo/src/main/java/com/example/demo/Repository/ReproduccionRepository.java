package com.example.demo.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.DTO.ReporteContenidoDTO;
import com.example.demo.Entity.ReproduccionEntity;

public interface ReproduccionRepository extends JpaRepository<ReproduccionEntity, Integer> {
    public ArrayList<ReproduccionEntity> findAll();

    @Query("""
                SELECT new com.example.demo.DTO.ReporteContenidoDTO(
                    r.contenido.titulo,
                    COUNT(r)
                )
                FROM ReproduccionEntity r
                GROUP BY r.contenido.titulo
                HAVING COUNT(r) > :n
            """)
    ArrayList<ReporteContenidoDTO> obtenerContenidosConMasDeNReproducciones(@Param("n") int n);

    ArrayList<ReproduccionEntity> findByUsuarioId(int usuarioId);

    @Query("SELECT AVG(r.calificacion) FROM ReproduccionEntity r WHERE r.contenido.id = :contenidoId")
    Double promedioCalificacion(@Param("contenidoId") int contenidoId);

    @Query("""
                SELECT r FROM ReproduccionEntity r
                WHERE DATE(r.fechaHora) = :fecha
            """)
    ArrayList<ReproduccionEntity> findByFecha(@Param("fecha") LocalDate fecha);

    List<ReproduccionEntity> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
}
