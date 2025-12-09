package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    public ArrayList<UsuarioEntity> findAll();

    @Query("""
            SELECT u FROM UsuarioEntity u
            WHERE (:tipo IS NULL OR TYPE(u) =
               CASE
                  WHEN :tipo = 'PREMIUM' THEN UsuarioPremiumEntity
                  WHEN :tipo = 'ESTANDAR' THEN UsuarioEstandarEntity
               END)
            AND (:desde IS NULL OR u.fechaRegistro >= :desde)
            AND (:hasta IS NULL OR u.fechaRegistro <= :hasta)
            """)
    ArrayList<UsuarioEntity> filtrarUsuarios(@Param("tipo") String tipo, @Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);
}