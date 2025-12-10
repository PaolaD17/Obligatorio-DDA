package com.example.demo.Service;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Entity.UsuarioEstandarEntity;
import com.example.demo.Entity.UsuarioPremiumEntity;
import com.example.demo.Repository.UsuarioRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UsuarioEntity agregarUsuario(UsuarioDTO dto) {

        UsuarioEntity usuario;

        if (dto.getTipoUsuario().equalsIgnoreCase("PREMIUM")) {
            UsuarioPremiumEntity premium = new UsuarioPremiumEntity();
            premium.setNombreCompleto(dto.getNombreCompleto());
            premium.setEmail(dto.getEmail());
            premium.setFechaRegistro(dto.getFechaRegistro());
            premium.setFechaInicioMembresia(dto.getFechaMembresia());

            usuario = premium;
        } else {
            UsuarioEstandarEntity estandar = new UsuarioEstandarEntity();
            estandar.setNombreCompleto(dto.getNombreCompleto());
            estandar.setEmail(dto.getEmail());
            estandar.setFechaRegistro(dto.getFechaRegistro());

            usuario = estandar;
        }

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("El email ya está en uso");
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public ArrayList<UsuarioEntity> listarUsuarios() {
        return new ArrayList<>(usuarioRepository.findAll());
    }

    @Override
    public UsuarioEntity obtenerUsuarioPorId(int id) {
        Optional<UsuarioEntity> usuarioOpt = usuarioRepository.findById(id);
        return usuarioOpt.orElse(null);
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioEntity modificarUsuario(UsuarioDTO dto, int id) {
        UsuarioEntity usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente == null) {
            return null;
        }

        String tipoNuevo = dto.getTipoUsuario();
        String tipoActual = usuarioExistente instanceof UsuarioPremiumEntity ? "PREMIUM" : "ESTANDAR";

        String nombre = dto.getNombreCompleto();
        String email = dto.getEmail();
        LocalDate fechaRegistro = dto.getFechaRegistro();
        LocalDate fechaMembresia = dto.getFechaMembresia();

        if (!tipoNuevo.equalsIgnoreCase(tipoActual)) {
            usuarioRepository.deleteById(usuarioExistente.getId());

            UsuarioEntity nuevoUsuario;
            if (tipoNuevo.equalsIgnoreCase("PREMIUM")) {
                UsuarioPremiumEntity premium = new UsuarioPremiumEntity();
                premium.setFechaInicioMembresia(fechaMembresia);
                nuevoUsuario = premium;
            } else {
                UsuarioEstandarEntity estandar = new UsuarioEstandarEntity();
                nuevoUsuario = estandar;
            }

            nuevoUsuario.setNombreCompleto(nombre);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setFechaRegistro(fechaRegistro);

            return usuarioRepository.save(nuevoUsuario);
        } else {
            usuarioExistente.setNombreCompleto(nombre);
            usuarioExistente.setEmail(email);
            usuarioExistente.setFechaRegistro(fechaRegistro);

            if (usuarioExistente instanceof UsuarioPremiumEntity) {
                ((UsuarioPremiumEntity) usuarioExistente).setFechaInicioMembresia(fechaMembresia);
            }

            return usuarioRepository.save(usuarioExistente);
        }
    }

    @Override
    public ArrayList<UsuarioEntity> filtrarUsuarios(String tipo, LocalDate desde, LocalDate hasta) {

        ArrayList<UsuarioEntity> lista = usuarioRepository.filtrarUsuarios(desde, hasta);

        if (tipo == null || tipo.isEmpty()) {
            return lista;
        }

        ArrayList<UsuarioEntity> filtrados = new ArrayList<>();

        for (UsuarioEntity u : lista) {
            if (tipo.equalsIgnoreCase("premium") && u instanceof UsuarioPremiumEntity) {
                filtrados.add(u);
            }
            if (tipo.equalsIgnoreCase("estandar") && u instanceof UsuarioEstandarEntity) {
                filtrados.add(u);
            }
        }
        return filtrados;
    }
}
