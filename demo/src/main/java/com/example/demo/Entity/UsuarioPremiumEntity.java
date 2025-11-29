package com.example.demo.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class UsuarioPremiumEntity extends UsuarioEntity {

    private LocalDate fechaInicioMembresia;

    private double descuento = 0.20; // 20% de descuento en contenidos

    private boolean accesoExclusivo = true; // Acceso a contenidos exclusivos

    public UsuarioPremiumEntity(int id, String nombreCompleto, String email, LocalDate fechaRegistro, LocalDate fechaInicioMembresia) {
        super (id, nombreCompleto, email, fechaRegistro);
        this.fechaInicioMembresia = fechaInicioMembresia;
    }

    public UsuarioPremiumEntity() {
    }

    @Override
    public String toString() {
        return "UsuarioPremiumEntity [id=" + getId() + ", nombreCompleto=" + getNombreCompleto() + ", email=" + getEmail() + ", fechaRegistro=" + getFechaRegistro() + ", fechaInicioMembresia=" + fechaInicioMembresia + ", descuento=" + descuento + ", accesoExclusivo=" + accesoExclusivo + "]";
    }
}