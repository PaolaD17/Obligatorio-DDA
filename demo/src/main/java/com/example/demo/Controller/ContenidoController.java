package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.ContenidoEntity;
import com.example.demo.Service.ContenidoService;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;

    @PostMapping()
    public ContenidoEntity agregarContenido(@RequestBody ContenidoEntity contenido) {
        return contenidoService.agregarContenido(contenido);
    }

    @GetMapping()
    public ArrayList<ContenidoEntity> listarContenido() {
        return contenidoService.listarContenido();
    }
    
    @GetMapping("/{id}")
    public ContenidoEntity obtenerContenido(@PathVariable int id) {
        return contenidoService.obtenerContenidoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarContenido(@PathVariable int id) {
        contenidoService.eliminarContenido(id);
    }
}
