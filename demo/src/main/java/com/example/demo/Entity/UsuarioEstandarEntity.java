package com.example.demo.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Usuario_Estandar")
public class UsuarioEstandarEntity extends UsuarioEntity {

    public UsuarioEstandarEntity(int id, String nombreCompleto, String email, LocalDate fechaRegistro, List<ReproduccionEntity> reproducciones) {
        super (id, nombreCompleto, email, fechaRegistro, reproducciones);
    }

    public UsuarioEstandarEntity() {
    }
    
}