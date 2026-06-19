package com.examen.pokemon.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Debe ser un email valido")
    private String email;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
