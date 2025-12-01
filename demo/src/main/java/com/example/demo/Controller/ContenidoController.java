package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.ContenidoService;

@RestController
@RequestMapping("Contenidos/dos")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;




}
