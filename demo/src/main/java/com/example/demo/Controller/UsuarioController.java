package com.example.demo.Controller;

import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Service.UsuarioService;

import java.util.ArrayList;

import com.example.demo.DTO.UsuarioDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping()
    public ResponseEntity<?> agregarUsuario(@RequestBody UsuarioDTO dto) {
        try {
            UsuarioEntity nuevo = usuarioService.agregarUsuario(dto);
            return ResponseEntity.ok(nuevo);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @GetMapping()
    public ArrayList<UsuarioEntity> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @GetMapping("/{id}")
    public UsuarioEntity obtenerUsuario(@PathVariable int id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/{id}")
    public UsuarioEntity modificarUsuario(@PathVariable int id, @RequestBody UsuarioDTO dto) {
        return usuarioService.modificarUsuario(dto, id);
    }
}