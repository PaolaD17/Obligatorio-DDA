package com.example.demo.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
      public ArrayList<UsuarioEntity> findAll();

      boolean existsByEmail(String email);

      @Query("""
                      SELECT u FROM UsuarioEntity u
                      WHERE (:desde IS NULL OR u.fechaRegistro >= :desde)
                      AND (:hasta IS NULL OR u.fechaRegistro <= :hasta)
                  """)
      ArrayList<UsuarioEntity> filtrarUsuarios(
                  @Param("desde") LocalDate desde,
                  @Param("hasta") LocalDate hasta);
}