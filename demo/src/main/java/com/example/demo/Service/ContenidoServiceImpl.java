package com.example.demo.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ReporteContenidoDTO;
import com.example.demo.Entity.ContenidoEntity;
import com.example.demo.Entity.ReproduccionEntity;
import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Entity.UsuarioPremiumEntity;
import com.example.demo.Repository.ContenidoRepository;
import com.example.demo.Repository.ReproduccionRepository;
import com.example.demo.Repository.UsuarioRepository;

@Service
public class ContenidoServiceImpl implements ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;
    @Autowired
    private ReproduccionRepository reproduccionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public ContenidoEntity agregarContenido(ContenidoEntity contenido) {

        // Validaciones
        int anioActual = java.time.Year.now().getValue();

        if (contenido.getAnioEstreno() < 1888 || contenido.getAnioEstreno() > anioActual) {
            throw new RuntimeException("El año de estreno debe estar entre 1888 y " + anioActual);
        }

        if (contenido.getDuracion() <= 0) {
            throw new RuntimeException("La duración debe ser mayor a 0 minutos");
        }

        if (contenido.getPrecioSuscripcion() == null
                || contenido.getPrecioSuscripcion().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new RuntimeException("El año de estreno debe estar entre 1888 y " + java.time.Year.now().getValue());
        }

        // Todo OK, guardamos
        return contenidoRepository.save(contenido);
    }

    @Override
    public ArrayList<ContenidoEntity> listarContenido() {
        return contenidoRepository.findAll();
    }

    public ArrayList<ContenidoEntity> listarContenidosParaUsuario(int usuarioId) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        UsuarioEntity usuario = usuarioOpt.get();

        if (usuario instanceof UsuarioPremiumEntity) {
            return contenidoRepository.findAll();
        } else {
            return contenidoRepository.findByExclusivoPremiumFalse();
        }
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

        // Validaciones claras
        int anioActual = java.time.Year.now().getValue();

        if (nuevo.getAnioEstreno() < 1888 || nuevo.getAnioEstreno() > anioActual) {
            throw new RuntimeException("El año de estreno debe estar entre 1888 y " + anioActual);
        }

        if (nuevo.getDuracion() <= 0) {
            throw new RuntimeException("La duración debe ser mayor a 0 minutos");
        }

        if (nuevo.getPrecioSuscripcion() == null
                || nuevo.getPrecioSuscripcion().compareTo(java.math.BigDecimal.ZERO) < 0) {
            throw new RuntimeException("El año de estreno debe estar entre 1888 y " + java.time.Year.now().getValue());
        }

        // Si pasó las validaciones, se actualizan los campos
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
    public ArrayList<ReporteContenidoDTO> obtenerContenidosConMasDeNReproducciones(int n) {
        return reproduccionRepository.obtenerContenidosConMasDeNReproducciones(n);
    }

    @Override
    public ArrayList<ContenidoEntity> obtenerContenidosReproducidosEnFecha(LocalDate fecha) {

        LocalDateTime inicio = fecha.atStartOfDay();
        LocalDateTime fin = fecha.atTime(23, 59, 59);

        List<ReproduccionEntity> reproducciones = reproduccionRepository.findByFechaHoraBetween(inicio, fin);

        Set<ContenidoEntity> contenidos = new HashSet<>();

        for (ReproduccionEntity r : reproducciones) {
            contenidos.add(r.getContenido());
        }

        return new ArrayList<>(contenidos);
    }

}