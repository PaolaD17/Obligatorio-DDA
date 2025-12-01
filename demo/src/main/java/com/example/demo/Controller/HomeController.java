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

    @GetMapping("/usuarios")
    public String usuarios() {
        return "forward:/UsuariosListar.html";
    }
}
