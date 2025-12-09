package com.example.demo.Service;

import java.util.ArrayList;

import com.example.demo.DTO.ReproduccionDTO;
import com.example.demo.Entity.ReproduccionEntity;

public interface ReproduccionService {
    ReproduccionEntity agregarReproduccion(ReproduccionDTO dto);

    public ArrayList<ReproduccionEntity> listarReproduccion();

    public ReproduccionEntity obtenerReproduccionPorId(int id);

    public void eliminarReproduccion(int id);

    public ReproduccionEntity modificarReproduccion(ReproduccionEntity reproduccion, int id);

    public ArrayList<ReproduccionEntity> obtenerReproduccionesPorUsuario(int usuarioId);

    public Double promedioCalificacion(int contenidoId);
}
