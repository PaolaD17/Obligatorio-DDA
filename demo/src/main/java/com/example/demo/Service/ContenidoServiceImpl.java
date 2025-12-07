package com.example.demo.Service;

import java.util.ArrayList;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContenidoEntity;
import com.example.demo.Repository.ContenidoRepository;

@Service
public class ContenidoServiceImpl implements ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;


    @Override
    public ContenidoEntity agregarContenido(ContenidoEntity contenido) {
        return contenidoRepository.save(contenido);
    }

    @Override
    public ArrayList<ContenidoEntity> listarContenido() {
        return contenidoRepository.findAll();
    }

    @Override
    public ContenidoEntity obtenerContenidoPorId(int id) {
        Optional<ContenidoEntity> contenidoOpt = contenidoRepository.findById(id);
        return contenidoOpt.orElse(null);
    }

    @Override
    public void eliminarContenido(int id) {
        contenidoRepository.deleteById(id);
    }

    @Override
    public ContenidoEntity modificarContenido(ContenidoEntity contenido, int id) {
        ContenidoEntity CExistente = contenidoRepository.findById(id).orElse(null);
        if (CExistente != null) {
            CExistente.setTitulo(contenido.getTitulo());
            CExistente.setDescripcion(contenido.getDescripcion());
            CExistente.setCategoria(contenido.getCategoria());
            CExistente.setDuracion(contenido.getDuracion());
            CExistente.setAnioEstreno(contenido.getAnioEstreno());
            CExistente.setPrecioSuscripcion(contenido.getPrecioSuscripcion());
            CExistente.setPortadaUrl(contenido.getPortadaUrl());
            CExistente.setTrailerUrl(contenido.getTrailerUrl());
            CExistente.setReproducciones(contenido.getReproducciones());
            CExistente.setExclusivoPremium(contenido.isExclusivoPremium());

            return contenidoRepository.save(CExistente);
        }
        return null;
    }
}
