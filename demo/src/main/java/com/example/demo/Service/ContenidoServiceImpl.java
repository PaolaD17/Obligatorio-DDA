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
    public ContenidoEntity modificarContenido(ContenidoEntity nuevo, int id) {

        ContenidoEntity existente = contenidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));

        existente.setTitulo(nuevo.getTitulo());
        existente.setDescripcion(nuevo.getDescripcion());
        existente.setCategoria(nuevo.getCategoria());
        existente.setDuracion(nuevo.getDuracion());
        existente.setAnioEstreno(nuevo.getAnioEstreno());
        existente.setTipoOperacion(nuevo.getTipoOperacion());
        existente.setPrecioSuscripcion(nuevo.getPrecioSuscripcion());
        existente.setExclusivoPremium(nuevo.isExclusivoPremium());

        return contenidoRepository.save(existente);
    }

}
