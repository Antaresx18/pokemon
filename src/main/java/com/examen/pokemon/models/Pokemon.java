package com.examen.pokemon.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "pokemon")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "generacion")
    private Integer generacion;

    @Column(name = "fecha_descubrimiento")
    private LocalDate fechaDescubrimiento;

    @Column(name = "uuid", length = 100, unique = true, nullable = false)
    private String uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_pokemon_id", nullable = false)
    private TipoPokemon tipoPokemon;

    @PrePersist
    public void prePersist() {
        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getGeneracion() { return generacion; }
    public void setGeneracion(Integer generacion) { this.generacion = generacion; }

    public LocalDate getFechaDescubrimiento() { return fechaDescubrimiento; }
    public void setFechaDescubrimiento(LocalDate fechaDescubrimiento) { this.fechaDescubrimiento = fechaDescubrimiento; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public TipoPokemon getTipoPokemon() { return tipoPokemon; }
    public void setTipoPokemon(TipoPokemon tipoPokemon) { this.tipoPokemon = tipoPokemon; }
}
