package com.examen.pokemon.repositories;

import com.examen.pokemon.models.TipoPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoPokemonRepository extends JpaRepository<TipoPokemon, Long> {
    Optional<TipoPokemon> findByUuid(String uuid);
}
