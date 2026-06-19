package com.examen.pokemon.controllers;

import com.examen.pokemon.dto.PokemonRequest;
import com.examen.pokemon.models.Pokemon;
import com.examen.pokemon.services.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controlador para los endpoints relacionados a Pokemon.
 * Maneja: Listar pokemones por tipo, registrar un pokemon.
 */
@RestController
@RequestMapping("/pokemons")
@Tag(name = "Pokemon", description = "Endpoints relacionados al registro y consulta de Pokemones")
public class PokemonController {

    @Autowired
    private PokemonService pokemonService;

    /**
     * SERVICIO 2: GET /pokemons/{tipo}
     * Lista todos los pokemones de un tipo especifico (el tipo es el UUID del TipoPokemon).
     */
    @GetMapping("/{tipo}")
    @Operation(
        summary = "Listar pokemones por tipo",
        description = "Retorna todos los pokemones registrados en el sistema que pertenecen al tipo indicado. El parametro 'tipo' es el UUID del tipo de pokemon."
    )
    public ResponseEntity<?> listarPokemonesPorTipo(
            @Parameter(description = "UUID del tipo de pokemon") @PathVariable String tipo) {
        try {
            List<Pokemon> pokemones = pokemonService.listarPokemonesPorTipo(tipo);
            return ResponseEntity.ok(pokemones);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * SERVICIO 3: POST /pokemons
     * Registra un nuevo Pokemon en el sistema.
     */
    @PostMapping
    @Operation(
        summary = "Registrar un Pokemon",
        description = "Crea y guarda un nuevo Pokemon en el sistema. Se debe proporcionar el UUID del tipo de pokemon."
    )
    public ResponseEntity<?> registrarPokemon(@Valid @RequestBody PokemonRequest request) {
        try {
            Pokemon pokemon = pokemonService.registrarPokemon(request);
            return ResponseEntity.ok(pokemon);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
