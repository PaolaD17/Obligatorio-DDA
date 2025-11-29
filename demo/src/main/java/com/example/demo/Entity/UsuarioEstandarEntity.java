package com.example.demo.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class UsuarioEstandarEntity extends UsuarioEntity {

    public UsuarioEstandarEntity(int id, String nombreCompleto, String email, LocalDate fechaRegistro) {
        super (id, nombreCompleto, email, fechaRegistro);
    }

    public UsuarioEstandarEntity() {
    }

    @Override
    public String toString() {
        return "UsuarioEstandarEntity [id=" + getId() + ", nombreCompleto=" + getNombreCompleto() + ", email=" + getEmail() + ", fechaRegistro=" + getFechaRegistro() + "]";
    }
}