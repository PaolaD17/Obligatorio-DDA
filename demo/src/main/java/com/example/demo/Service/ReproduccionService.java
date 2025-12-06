package com.example.demo.Service;

import java.util.ArrayList;

import com.example.demo.Entity.ReproduccionEntity;

public interface ReproduccionService {
    public ReproduccionEntity agregarReproduccion(ReproduccionEntity contenido);

    public ArrayList<ReproduccionEntity> listarReproduccion();

    public ReproduccionEntity obtenerReproduccionPorId(int id);

    public void eliminarReproduccion(int id);

    public ReproduccionEntity modificarReproduccion(ReproduccionEntity reproduccion, int id);
}
