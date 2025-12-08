package com.example.demo.DTO;

import java.time.LocalDate;

public class UsuarioDTO {

    private String nombreCompleto;
    private String email;
    private LocalDate fechaRegistro;
    private String tipoUsuario;
    private LocalDate fechaMembresia;

    public UsuarioDTO() {}

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(LocalDate fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }

    public LocalDate getFechaMembresia() { return fechaMembresia; }
    public void setFechaMembresia(LocalDate fechaMembresia) { this.fechaMembresia = fechaMembresia; }
}