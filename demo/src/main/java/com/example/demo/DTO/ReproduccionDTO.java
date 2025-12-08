package com.example.demo.DTO;

import java.time.LocalDateTime;

public class ReproduccionDTO {
    private int usuarioId;
    private int contenidoId;
    private LocalDateTime fechaHora;
    private int duracionMinutos;
    private int calificacion;

    public int getUsuarioId() { 
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId; 
    }

    public int getContenidoId() {
        return contenidoId;
    }
    public void setContenidoId(int contenidoId) {
        this.contenidoId = contenidoId;
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
}
