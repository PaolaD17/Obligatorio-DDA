package com.example.demo.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.ReproduccionDTO;
import com.example.demo.Entity.ContenidoEntity;
import com.example.demo.Entity.ReproduccionEntity;
import com.example.demo.Service.ContenidoService;
import com.example.demo.Service.ReproduccionService;

@RestController
@RequestMapping("/api/reproducciones")
public class ReproduccionController {
    @Autowired
    private ReproduccionService reproduccionService;

    @Autowired
    private ContenidoService contenidoService;

    @PostMapping()
    public ReproduccionEntity agregarReproduccion(@RequestBody ReproduccionDTO dto) {
        return reproduccionService.agregarReproduccion(dto);
    }

    @GetMapping()
    public ArrayList<ReproduccionEntity> listarReproduccion() {
        return reproduccionService.listarReproduccion();
    }

    @GetMapping("/{id}")
    public ReproduccionEntity obtenerReproduccion(@PathVariable int id) {
        return reproduccionService.obtenerReproduccionPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarReproduccion(@PathVariable int id) {
        reproduccionService.eliminarReproduccion(id);
    }

    @PutMapping("/{id}")
    public ReproduccionEntity modificarReproduccion(@PathVariable int id,
            @RequestBody ReproduccionEntity reproduccion) {
        return reproduccionService.modificarReproduccion(reproduccion, id);
    }

    @GetMapping("/contenidos-usuario/{usuarioId}")
    public ArrayList<ContenidoEntity> listarContenidosParaUsuario(@PathVariable int usuarioId) {
        return contenidoService.listarContenidosParaUsuario(usuarioId);
    }

}