package com.example.demo.DTO;

public class ReporteContenidoDTO {
    private String titulo;
    private Long totalReproducciones;

    public ReporteContenidoDTO(String titulo, Long totalReproducciones) {
        this.titulo = titulo;
        this.totalReproducciones = totalReproducciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getTotalReproducciones() {
        return totalReproducciones;
    }
}
