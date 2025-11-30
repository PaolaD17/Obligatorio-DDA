package com.example.demo.Service;

import java.util.List;

import com.example.demo.Entity.UsuarioEntity;

public interface UsuarioService {
    List<UsuarioEntity> getUsuarios();

    UsuarioEntity postUsuario(UsuarioEntity usuario);
}
