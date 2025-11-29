package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class ReproduccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    // Relación con Contenido
    @ManyToOne
    @JoinColumn(name = "contenido_id")
    private ContenidoEntity contenido;

    @NotNull(message = "La fecha y hora de reproducción no puede ser nula")
    private LocalDateTime fechaHora;

    @Min(1)
    private int duracionMinutos;

    @Min(1)
    @Max(5)
    private int calificacion; // 1–5 estrellas

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ContenidoEntity getContenido() {
        return contenido;
    }

    public void setContenido(ContenidoEntity contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public ReproduccionEntity(int id, UsuarioEntity usuario, ContenidoEntity contenido, LocalDateTime fechaHora,
            int duracionMinutos, int calificacion) {
        this.id = id;
        this.usuario = usuario;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
        this.duracionMinutos = duracionMinutos;
        this.calificacion = calificacion;
    }

    public ReproduccionEntity() {
    }
    
    @Override
    public String toString() {
        return "ReproduccionEntity [id=" + id + ", usuario=" + usuario + ", contenido=" + contenido + ", fechaHora="
                + fechaHora + ", duracionMinutos=" + duracionMinutos + ", calificacion=" + calificacion + "]";
    }
}