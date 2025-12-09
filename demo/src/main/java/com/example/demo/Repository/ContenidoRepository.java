package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.ContenidoEntity;

public interface ContenidoRepository extends JpaRepository<ContenidoEntity, Integer> {
    public ArrayList<ContenidoEntity> findAll();

}
