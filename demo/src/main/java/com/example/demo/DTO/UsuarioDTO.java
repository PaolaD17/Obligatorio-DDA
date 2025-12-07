package com.example.demo.DTO;

import java.time.LocalDate;

public class UsuarioDTO {

    public String nombreCompleto;
    public String email;
    public LocalDate fechaRegistro;
    public String tipoUsuario; // "ESTANDAR" o "PREMIUM"
    public LocalDate fechaMembresia; // solo si es premium
}
