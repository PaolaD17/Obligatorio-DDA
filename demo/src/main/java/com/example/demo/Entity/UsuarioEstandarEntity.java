package com.example.demo.Entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;

@Entity
public class UsuarioEstandarEntity extends UsuarioEntity {

    public UsuarioEstandarEntity(int id, String nombreCompleto, String email, LocalDate fechaRegistro, List<ReproduccionEntity> reproducciones) {
        super (id, nombreCompleto, email, fechaRegistro, reproducciones);
    }

    public UsuarioEstandarEntity() {
    }
    
}