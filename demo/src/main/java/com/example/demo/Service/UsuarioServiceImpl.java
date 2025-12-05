package com.example.demo.Service;

import com.example.demo.Entity.UsuarioEntity;
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
    public UsuarioEntity agregarUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public ArrayList<UsuarioEntity> listarUsuarios() {
        return usuarioRepository.findAll();
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
}
