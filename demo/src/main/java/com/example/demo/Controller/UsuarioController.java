package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.UsuarioService;

@RestController
@RequestMapping("Usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
}
