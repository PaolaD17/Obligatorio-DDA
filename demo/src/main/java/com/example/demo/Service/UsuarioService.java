package com.example.demo.Service;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.Entity.UsuarioEntity;

import java.util.ArrayList;

public interface UsuarioService {

    public UsuarioEntity agregarUsuario(UsuarioDTO dto);

    public ArrayList<UsuarioEntity> listarUsuarios();

    public UsuarioEntity obtenerUsuarioPorId(int id);

    public void eliminarUsuario(int id);
    
    public UsuarioEntity modificarUsuario(UsuarioEntity usuario, int id);
}
