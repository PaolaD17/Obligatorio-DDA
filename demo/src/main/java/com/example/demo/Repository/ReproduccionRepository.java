package com.example.demo.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entity.ReproduccionEntity;

public interface ReproduccionRepository extends JpaRepository<ReproduccionEntity, Integer> {
    public ArrayList<ReproduccionEntity> findAll();
}
