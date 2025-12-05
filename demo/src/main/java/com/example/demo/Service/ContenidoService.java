package com.example.demo.Service;

import java.util.ArrayList;

import com.example.demo.Entity.ContenidoEntity;

public interface ContenidoService {
    public ContenidoEntity agregarContenido(ContenidoEntity contenido);

    public ArrayList<ContenidoEntity> listarContenido();

    public ContenidoEntity obtenerContenidoPorId(int id);

    public void eliminarContenido(int id);
}
