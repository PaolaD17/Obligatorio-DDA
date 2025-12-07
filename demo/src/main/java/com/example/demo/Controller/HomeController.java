package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.UsuarioService;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    //HOME
    @GetMapping("")
    public String home() {
        return "forward:/index.html";
    }

    //CONTENIDOS
    @GetMapping("/contenidos")
    public String contenidos() {
        return "forward:/Contenidos.html";
    }

    @GetMapping("/contenidos/nuevo")
    public String nuevoContenido() {
        return "redirect:/ContenidosAgregar.html";
    }

    @GetMapping("/contenidos/detalle")
    public String contenidosDetalle() {
        return "forward:/ContenidoDetalle.html";
    }

    @GetMapping("/contenidos/modificar/{id}")
    public String contenidosModificar(@PathVariable int id, Model model) {
        model.addAttribute("contenidoId", id); // pasar el ID al HTML
        return "forward:/ContenidosModificar.html";
    }

    //USUARIOS
    @GetMapping("/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("Usuarios", usuarioService.listarUsuarios());
        return "forward:/Usuarios.html";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario() {
        return "redirect:/UsuariosAgregar.html";
    }

    //REPRODUCCIONES
    @GetMapping("/reproducciones")
    public String reproducciones() {
        return "forward:/Reproduccion.html";
    }

    @GetMapping("/reproducciones/nuevo")
    public String nuevaReproduccion() {
        return "redirect:/ReproduccionesAgregar.html";
    }
}