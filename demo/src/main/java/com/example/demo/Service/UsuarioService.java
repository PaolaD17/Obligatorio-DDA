package com.example.demo.Service;

import com.example.demo.Entity.UsuarioEntity;
import java.util.List;

public interface UsuarioService {

    List<UsuarioEntity> getUsuarios();

    UsuarioEntity postUsuario(UsuarioEntity usuario);
}
