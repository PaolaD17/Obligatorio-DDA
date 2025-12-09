package com.example.demo.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.ContenidoEntity;
import com.example.demo.Entity.ReproduccionEntity;
import com.example.demo.Repository.ContenidoRepository;
import com.example.demo.Repository.ReproduccionRepository;

@Service
public class ContenidoServiceImpl implements ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;
    @Autowired
    private ReproduccionRepository reproduccionRepository;

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

    @Override
    public ArrayList<ContenidoEntity> obtenerContenidosConMasDeNReproducciones(int n) {

        ArrayList<Object[]> resultado = reproduccionRepository.obtenerContenidosConMasDeNReproducciones(n);
        ArrayList<ContenidoEntity> contenidos = new ArrayList<>();

        for (Object[] fila : resultado) {
            int contenidoId = (int) fila[0];
            contenidos.add(contenidoRepository.findById(contenidoId).get());
        }
        return contenidos;
    }

    @Override
    public ArrayList<ContenidoEntity> obtenerContenidosReproducidosEnFecha(LocalDateTime fechaHora) {

        ArrayList<ReproduccionEntity> reproducciones = reproduccionRepository.findByFechaHora(fechaHora);
        Set<ContenidoEntity> contenidos = new HashSet<>();

        for (ReproduccionEntity r : reproducciones) {
            contenidos.add(r.getContenido());
        }
        return new ArrayList<>(contenidos);
    }
}
