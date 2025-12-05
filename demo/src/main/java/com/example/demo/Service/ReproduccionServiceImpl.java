package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ReproduccionEntity;
import com.example.demo.Repository.ReproduccionRepository;

@Service
public class ReproduccionServiceImpl implements ReproduccionService {
    @Autowired
    private ReproduccionRepository reproduccionRepository;


    @Override
    public ReproduccionEntity agregarReproduccion(ReproduccionEntity reproduccion) {
        return reproduccionRepository.save(reproduccion);
    }

    @Override
    public ArrayList<ReproduccionEntity> listarReproduccion() {
        return reproduccionRepository.findAll();
    }

    @Override
    public ReproduccionEntity obtenerReproduccionPorId(int id) {
        Optional<ReproduccionEntity> reproduccionOpt = reproduccionRepository.findById(id);
        return reproduccionOpt.orElse(null);
    }

    @Override
    public void eliminarReproduccion(int id) {
        reproduccionRepository.deleteById(id);
    }
}
