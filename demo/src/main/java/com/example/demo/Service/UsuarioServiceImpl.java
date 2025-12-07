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

        if (dto.tipoUsuario.equalsIgnoreCase("PREMIUM")) {
            UsuarioPremiumEntity premium = new UsuarioPremiumEntity();
            premium.setNombreCompleto(dto.nombreCompleto);
            premium.setEmail(dto.email);
            premium.setFechaRegistro(dto.fechaRegistro);
            premium.setFechaInicioMembresia(dto.fechaMembresia);

            usuario = premium;
        } else {
            UsuarioEstandarEntity estandar = new UsuarioEstandarEntity();
            estandar.setNombreCompleto(dto.nombreCompleto);
            estandar.setEmail(dto.email);
            estandar.setFechaRegistro(dto.fechaRegistro);

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
    public UsuarioEntity modificarUsuario(UsuarioEntity usuario, int id) {
        UsuarioEntity UExistente = usuarioRepository.findById(id).orElse(null);
        if (UExistente != null) {
            UExistente.setNombreCompleto(usuario.getNombreCompleto());
            UExistente.setEmail(usuario.getEmail());
            UExistente.setFechaRegistro(usuario.getFechaRegistro());

            return usuarioRepository.save(UExistente);
        }
        return null;
    }
}
