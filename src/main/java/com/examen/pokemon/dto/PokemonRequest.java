package com.examen.pokemon.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class PokemonRequest {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;
    private LocalDate fechaDescubrimiento;
    private Integer generacion;

    @NotBlank(message = "El UUID del tipo de pokemon es obligatorio")
    private String tipoPokemon;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDate getFechaDescubrimiento() { return fechaDescubrimiento; }
    public void setFechaDescubrimiento(LocalDate fechaDescubrimiento) { this.fechaDescubrimiento = fechaDescubrimiento; }

    public Integer getGeneracion() { return generacion; }
    public void setGeneracion(Integer generacion) { this.generacion = generacion; }

    public String getTipoPokemon() { return tipoPokemon; }
    public void setTipoPokemon(String tipoPokemon) { this.tipoPokemon = tipoPokemon; }
}
