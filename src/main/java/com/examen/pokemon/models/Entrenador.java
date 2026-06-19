package com.examen.pokemon.models;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "entrenador")
public class Entrenador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "apellido", length = 50)
    private String apellido;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_vinculacion")
    private LocalDate fechaVinculacion;

    @Column(name = "uuid", length = 100, unique = true, nullable = false)
    private String uuid;

    @Column(name = "email", length = 150, unique = true, nullable = false)
    private String email;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "pokemon_captura",
        joinColumns = @JoinColumn(name = "entrenador_id"),
        inverseJoinColumns = @JoinColumn(name = "pokemon_id")
    )
    private List<Pokemon> pokemones = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.uuid == null || this.uuid.isEmpty()) {
            this.uuid = UUID.randomUUID().toString();
        }
        if (this.fechaVinculacion == null) {
            this.fechaVinculacion = LocalDate.now();
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public LocalDate getFechaVinculacion() { return fechaVinculacion; }
    public void setFechaVinculacion(LocalDate fechaVinculacion) { this.fechaVinculacion = fechaVinculacion; }

    public String getUuid() { return uuid; }
    public void setUuid(String uuid) { this.uuid = uuid; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public List<Pokemon> getPokemones() { return pokemones; }
    public void setPokemones(List<Pokemon> pokemones) { this.pokemones = pokemones; }
}
