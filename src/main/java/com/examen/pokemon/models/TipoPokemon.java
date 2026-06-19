package com.examen.pokemon.models;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "pokemon_tipo_pokemon")
public class TipoPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion", length = 100, nullable = false)
    private String descripcion;

    @Column(name = "uuid", length = 100, unique = true, nullable = false)
    private String uuid;

    @PrePersist
    public void prePersist() {
        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = UUID.randomUUID().toString();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }
}
