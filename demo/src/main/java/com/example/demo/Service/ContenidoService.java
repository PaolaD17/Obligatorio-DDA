package com.example.demo.Service;

import java.time.LocalDate;
import java.util.ArrayList;

import com.example.demo.DTO.ReporteContenidoDTO;
import com.example.demo.Entity.ContenidoEntity;

public interface ContenidoService {
    public ContenidoEntity agregarContenido(ContenidoEntity contenido);

    public ArrayList<ContenidoEntity> listarContenido();

    public ContenidoEntity obtenerContenidoPorId(int id);

    public void eliminarContenido(int id);

    public ContenidoEntity modificarContenido(ContenidoEntity contenido, int id);

    public ArrayList<ReporteContenidoDTO> obtenerContenidosConMasDeNReproducciones(int n);

    ArrayList<ContenidoEntity> obtenerContenidosReproducidosEnFecha(LocalDate fecha);
}