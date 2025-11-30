package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import com.example.demo.Entity.UsuarioEntity;
import com.example.demo.Service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public String mostrarLista(Model model) {
        List<UsuarioEntity> lista = usuarioService.getUsuarios();
        model.addAttribute("usuarios", lista);
        return "UsuariosListar";
    }

    @GetMapping("/agregar")
    public String mostrarFormulario(Model model) {
        model.addAttribute("usuario", new UsuarioEntity());
        return "UsuariosAgregar";
    }

    @PostMapping("/agregar")
    public String guardarUsuario(@ModelAttribute UsuarioEntity usuario) {
        usuarioService.postUsuario(usuario);
        return "redirect:/usuarios/listar";
    }
}