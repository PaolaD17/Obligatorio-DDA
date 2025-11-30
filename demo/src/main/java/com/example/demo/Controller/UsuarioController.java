package com.example.demo.Controller;

import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Service.UsuarioService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    //ESTO ES DE PRUEBA PARA VER SI ANDABA ALGO
    @PostConstruct
    public void init() {
        System.out.println("✅ UsuarioController CARGADO");
    }

    @Autowired
    private UsuarioService usuarioService;

    // ✅ LISTAR USUARIOS
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        return "UsuariosListar";   // ⚠️ SOLO EL NOMBRE, SIN /usuarios/
    }

    // ✅ FORMULARIO NUEVO USUARIO
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuarios", new UsuarioEntity());
        return "UsuariosAgregar"; // ⚠️ SOLO EL NOMBRE
    }

    // ✅ GUARDAR USUARIO
    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute UsuarioEntity usuario) {
        usuarioService.postUsuario(usuario);
        return "redirect:/usuarios/listar";
    }
}