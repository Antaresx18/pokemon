package com.examen.pokemon.repositories;

import com.examen.pokemon.models.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntrenadorRepository extends JpaRepository<Entrenador, Long> {
    Optional<Entrenador> findByEmail(String email);
    Optional<Entrenador> findByUuid(String uuid);
}
