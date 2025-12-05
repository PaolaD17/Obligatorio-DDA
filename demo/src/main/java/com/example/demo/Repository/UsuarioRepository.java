package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.UsuarioEntity;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
    public ArrayList<UsuarioEntity> findAll();
}