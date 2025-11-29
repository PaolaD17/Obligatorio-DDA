package com.example.demo.Entity;

import java.time.Duration;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ContenidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String titulo;

    private String descripcion;

    private String categoria;

    private Duration duracion;

    private int anioEstreno;

    private int precioSuscripcion;
    
    private String portadaUrl;

    private String trailerUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Duration getDuracion() {
        return duracion;
    }

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public int getanioEstreno() {
        return anioEstreno;
    }

    public void setanioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    public int getPrecioSuscripcion() {
        return precioSuscripcion;
    }

    public void setPrecioSuscripcion(int precioSuscripcion) {
        this.precioSuscripcion = precioSuscripcion;
    }

    public String getPortadaUrl() {
        return portadaUrl;
    }

    public void setPortadaUrl(String portadaUrl) {
        this.portadaUrl = portadaUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public ContenidoEntity(int id, String titulo, String descripcion, String categoria, Duration duracion,
            int anioEstreno, int precioSuscripcion, String portadaUrl, String trailerUrl) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.duracion = duracion;
        this.anioEstreno = anioEstreno;
        this.precioSuscripcion = precioSuscripcion;
        this.portadaUrl = portadaUrl;
        this.trailerUrl = trailerUrl;
    }

    public ContenidoEntity() {
    }

    @Override
    public String toString() {
        return "ContenidoEntity [id=" + id + ", titulo=" + titulo + ", descripcion=" + descripcion + ", categoria="
                + categoria + ", duracion=" + duracion + ", anioEstreno=" + anioEstreno + ", precioSuscripcion="
                + precioSuscripcion + ", portadaUrl=" + portadaUrl + ", trailerUrl=" + trailerUrl + "]";
    }
}