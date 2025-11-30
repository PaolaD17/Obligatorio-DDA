package com.example.demo.Service;

import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioEntity> getUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public UsuarioEntity postUsuario(UsuarioEntity usuario) {
        return usuarioRepository.save(usuario);
    }
}
