package com.example.demo.Entity;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class ContenidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;

    @NotBlank(message = "La descripción no puede estar vacía")
    private String descripcion;

    @NotBlank(message = "La categoría no puede estar vacía")
    private String categoria;

    @Min(1)
    private int duracion;

    @Min(1888)
    private int anioEstreno;

    @AssertTrue(message = "El año de estreno no puede ser mayor al actual")
    public boolean isAnioValido() {
        return anioEstreno <= Year.now().getValue();
    }

    @NotNull(message = "El precio de suscripción es obligatorio")
    @DecimalMin("0.0")
    private BigDecimal precioSuscripcion;
    
    private String portadaUrl;

    private String trailerUrl;

    @OneToMany(mappedBy = "contenido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReproduccionEntity> reproducciones;
    
    private boolean exclusivoPremium = false;

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

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAnioEstreno() {
        return anioEstreno;
    }

    public void setAnioEstreno(int anioEstreno) {
        this.anioEstreno = anioEstreno;
    }

    public BigDecimal getPrecioSuscripcion() {
        return precioSuscripcion;
    }

    public void setPrecioSuscripcion(BigDecimal precioSuscripcion) {
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

    public List<ReproduccionEntity> getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(List<ReproduccionEntity> reproducciones) {
        this.reproducciones = reproducciones;
    }

    public ContenidoEntity(int id, String titulo, String descripcion, String categoria, int duracion,
            int anioEstreno, BigDecimal precioSuscripcion, String portadaUrl, String trailerUrl, List<ReproduccionEntity> reproducciones, boolean exclusivoPremium) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.duracion = duracion;
        this.anioEstreno = anioEstreno;
        this.precioSuscripcion = precioSuscripcion;
        this.portadaUrl = portadaUrl;
        this.trailerUrl = trailerUrl;
        this.reproducciones = reproducciones;
        this.exclusivoPremium = exclusivoPremium;
    }

    public ContenidoEntity() {
    }

    @Override
    public String toString() {
        return "Contenido:\nId: " + id + "\nTítulo: " + titulo + "\nDescripción: " + descripcion + "\nCategoría: "
                + categoria + "\nDuración: " + duracion + "\nAño de estreno: " + anioEstreno + "\nPrecio de suscripción: "
                + precioSuscripcion + "\nPortada: " + portadaUrl + "\nTrailer: " + trailerUrl + "\nExclusivo: " + (exclusivoPremium ? "Sí" : "No");
    }
}