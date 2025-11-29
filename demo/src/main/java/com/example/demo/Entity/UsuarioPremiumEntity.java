package com.example.demo.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UsuarioPremiumEntity extends UsuarioEntity {

    private LocalDate fechaInicioMembresia;

    private double descuento = 0.20; // 20% de descuento en contenidos

    private boolean accesoExclusivo = true; // Acceso fijo a contenidos exclusivos

    public UsuarioPremiumEntity(int id, String nombreCompleto, String email, LocalDate fechaRegistro, LocalDate fechaInicioMembresia, List<ReproduccionEntity> reproducciones) {
        super(id, nombreCompleto, email, fechaRegistro, reproducciones);
        this.fechaInicioMembresia = fechaInicioMembresia;
    }

    public UsuarioPremiumEntity() {
    }

    @Override
    public String toString() {
        return "UsuarioPremiumEntity [id=" + getId() + ", nombreCompleto=" + getNombreCompleto() + ", email=" + getEmail() + ", fechaRegistro=" + getFechaRegistro() + ", fechaInicioMembresia=" + fechaInicioMembresia + ", descuento=" + descuento + ", accesoExclusivo=" + accesoExclusivo + "]";
    }
}