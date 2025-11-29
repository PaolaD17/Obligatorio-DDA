package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UsuarioPremiumEntity extends UsuarioEntity {

    @NotNull(message = "La fecha de inicio de membresía no puede ser nula")
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
        return "Usuario Premium\n" +
           "ID: " + getId() + "\n" +
           "Nombre completo: " + getNombreCompleto() + "\n" +
           "E-Mail: " + getEmail() + "\n" +
           "Fecha de registro: " + getFechaRegistro() + "\n" +
           "Reproducciones: " + (getReproducciones() != null ? getReproducciones().size() : 0) + "\n" +
           "Fecha de inicio de membresía: " + fechaInicioMembresia + "\n" +
           "Descuento: " + (descuento * 100) + "%\n" +
           "Acceso exclusivo: " + (accesoExclusivo ? "Sí" : "No");

    }
}