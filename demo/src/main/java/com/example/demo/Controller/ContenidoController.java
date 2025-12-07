package com.example.demo.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.ContenidoEntity;
import com.example.demo.Service.ContenidoService;
import com.example.demo.Service.UploadthingService;

@RestController
@RequestMapping("/api/contenidos")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;
    @Autowired
    private UploadthingService uploadthingService;

    @PostMapping("/upload")
    public ContenidoEntity agregarContenidoConMedia(
            @RequestParam("titulo") String titulo,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("categoria") String categoria,
            @RequestParam("duracion") int duracion,
            @RequestParam("anioEstreno") int anioEstreno,
            @RequestParam("precioSuscripcion") BigDecimal precio,
            @RequestParam("portada") MultipartFile portada,
            @RequestParam("trailer") MultipartFile trailer) {

        try {
            String portadaUrl = uploadthingService.uploadFile(portada);
            String trailerUrl = uploadthingService.uploadFile(trailer);

            ContenidoEntity contenido = new ContenidoEntity();
            contenido.setTitulo(titulo);
            contenido.setDescripcion(descripcion);
            contenido.setCategoria(categoria);
            contenido.setDuracion(duracion);
            contenido.setAnioEstreno(anioEstreno);
            contenido.setPrecioSuscripcion(precio);
            contenido.setPortadaUrl(portadaUrl);
            contenido.setTrailerUrl(trailerUrl);

            return contenidoService.agregarContenido(contenido);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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

    @PutMapping("/{id}")
    public ContenidoEntity modificarContenido(@PathVariable int id, @RequestBody ContenidoEntity contenido) {
        return contenidoService.modificarContenido(contenido, id);
    }
}
