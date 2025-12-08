package com.example.demo.Service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.ReproduccionDTO;
import com.example.demo.Entity.ContenidoEntity;
import com.example.demo.Entity.ReproduccionEntity;
import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Repository.ContenidoRepository;
import com.example.demo.Repository.ReproduccionRepository;
import com.example.demo.Repository.UsuarioRepository;

@Service
public class ReproduccionServiceImpl implements ReproduccionService {
    @Autowired
    private ReproduccionRepository reproduccionRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContenidoRepository contenidoRepository;

    @Override
    public ReproduccionEntity agregarReproduccion(ReproduccionDTO dto) {
        UsuarioEntity usuario = usuarioRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        ContenidoEntity contenido = contenidoRepository.findById(dto.getContenidoId())
                .orElseThrow(() -> new RuntimeException("Contenido no encontrado"));

        ReproduccionEntity reproduccion = new ReproduccionEntity();
        reproduccion.setUsuario(usuario);
        reproduccion.setContenido(contenido);
        reproduccion.setFechaHora(dto.getFechaHora());
        reproduccion.setDuracionMinutos(dto.getDuracionMinutos());
        reproduccion.setCalificacion(dto.getCalificacion());

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

    @Override
    public ReproduccionEntity modificarReproduccion(ReproduccionEntity reproduccion, int id) {
        ReproduccionEntity CExistente = reproduccionRepository.findById(id).orElse(null);
        if (CExistente != null) {
            CExistente.setUsuario(reproduccion.getUsuario());
            CExistente.setContenido(reproduccion.getContenido());
            CExistente.setFechaHora(reproduccion.getFechaHora());
            CExistente.setDuracionMinutos(reproduccion.getDuracionMinutos());
            CExistente.setCalificacion(reproduccion.getCalificacion());

            return reproduccionRepository.save(CExistente);
        }
        return null;
    }
}
