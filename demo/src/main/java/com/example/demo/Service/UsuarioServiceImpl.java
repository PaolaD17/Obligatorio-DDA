package com.example.demo.Service;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Entity.UsuarioEstandarEntity;
import com.example.demo.Entity.UsuarioPremiumEntity;
import com.example.demo.Repository.UsuarioRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        boolean esPremiumNuevo = dto.getTipoUsuario().equalsIgnoreCase("PREMIUM");
        boolean esPremiumActual = usuarioExistente instanceof UsuarioPremiumEntity;

        if (esPremiumNuevo != esPremiumActual) {

            usuarioRepository.deleteById(id);

            UsuarioEntity usuarioNuevo;

            if (esPremiumNuevo) {
                UsuarioPremiumEntity premium = new UsuarioPremiumEntity();
                premium.setFechaInicioMembresia(dto.getFechaMembresia());
                usuarioNuevo = premium;
            } else {
                usuarioNuevo = new UsuarioEstandarEntity();
            }

            usuarioNuevo.setNombreCompleto(dto.getNombreCompleto());
            usuarioNuevo.setEmail(dto.getEmail());
            usuarioNuevo.setFechaRegistro(dto.getFechaRegistro());

            return usuarioRepository.save(usuarioNuevo);
        }

        usuarioExistente.setNombreCompleto(dto.getNombreCompleto());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setFechaRegistro(dto.getFechaRegistro());

        if (usuarioExistente instanceof UsuarioPremiumEntity) {
            ((UsuarioPremiumEntity) usuarioExistente).setFechaInicioMembresia(dto.getFechaMembresia());
        }

        return usuarioRepository.save(usuarioExistente);
    }

}
