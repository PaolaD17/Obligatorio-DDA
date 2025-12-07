package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @GetMapping("")
    public String home() {
        return "forward:/index.html";
    }

    @GetMapping("/contenidos")
    public String contenidos() {
        return "forward:/Contenidos.html";
    }

    @GetMapping("/contenidos/nuevo")
    public String nuevoContenido() {
        return "redirect:/ContenidosAgregar.html";
    }

    @GetMapping("/usuarios")
    public String usuarios() {
        return "forward:/Usuarios.html";
    }

    @GetMapping("/usuarios/nuevo")
    public String nuevoUsuario() {
        return "redirect:/UsuariosAgregar.html";
    }

    @GetMapping("/reproducciones")
    public String reproducciones() {
        return "forward:/Reproduccion.html";
    }

    @GetMapping("/reproducciones/nuevo")
    public String nuevaReproduccion() {
        return "redirect:/ReproduccionesAgregar.html";
    }
}
