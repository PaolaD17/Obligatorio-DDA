package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombreCompleto;

    private String email;

    private LocalDate fechaRegistro;

    @OneToMany(mappedBy = "usuario")
    private List<ReproduccionEntity> reproducciones;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public UsuarioEntity(int id, String nombreCompleto, String email, LocalDate fechaRegistro, List<ReproduccionEntity> reproducciones) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.reproducciones = reproducciones;
    }

    public UsuarioEntity() {
    }

    @Override
    public String toString() {
        return "UsuarioEntity [id=" + id + ", nombreCompleto=" + nombreCompleto + ", email=" + email + ", fechaRegistro=" + fechaRegistro + ", reproducciones=" + reproducciones + "]";
    }
}