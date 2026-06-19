package com.examen.pokemon.repositories;

import com.examen.pokemon.models.Pokemon;
import com.examen.pokemon.models.TipoPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Optional<Pokemon> findByUuid(String uuid);
    List<Pokemon> findByTipoPokemon(TipoPokemon tipoPokemon);
}
