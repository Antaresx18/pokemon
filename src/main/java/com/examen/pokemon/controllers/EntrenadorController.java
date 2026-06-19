package com.examen.pokemon.controllers;

import com.examen.pokemon.dto.LoginRequest;
import com.examen.pokemon.services.EntrenadorService;
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
 * Controlador para los endpoints relacionados al Entrenador.
 * Maneja: Login, listar pokemones del entrenador, agregar pokemon al entrenador.
 */
@RestController
@RequestMapping("/entrenador")
@Tag(name = "Entrenador", description = "Endpoints relacionados al entrenador Pokemon")
public class EntrenadorController {

    @Autowired
    private EntrenadorService entrenadorService;

    @Autowired
    private PokemonService pokemonService;

    /**
     * SERVICIO 1: POST /entrenador/login
     * Recibe un email y retorna el UUID del entrenador.
     */
    @PostMapping("/login")
    @Operation(
        summary = "Login del entrenador",
        description = "Recibe el email del entrenador y retorna su UUID de identificacion"
    )
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            Map<String, String> response = entrenadorService.login(request.getEmail());
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * SERVICIO 4: GET /entrenador/{uuid}/pokemons
     * Lista todos los pokemones que tiene un entrenador.
     */
    @GetMapping("/{uuid}/pokemons")
    @Operation(
        summary = "Listar pokemones del entrenador",
        description = "Retorna la lista de pokemones registrados a un entrenador especifico"
    )
    public ResponseEntity<?> listarPokemonesDeEntrenador(
            @Parameter(description = "UUID del entrenador") @PathVariable String uuid) {
        try {
            return ResponseEntity.ok(pokemonService.listarPokemonesDeEntrenador(uuid));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * SERVICIO 5: POST /entrenador/{uuid}/pokemons/{uuidPokemon}
     * Agrega un pokemon al listado de pokemones de un entrenador.
     * Si ya esta asociado, retorna error.
     */
    @PostMapping("/{uuid}/pokemons/{uuidPokemon}")
    @Operation(
        summary = "Agregar pokemon al entrenador",
        description = "Asocia un pokemon al entrenador. Si ya esta registrado, retorna un mensaje de error."
    )
    public ResponseEntity<?> agregarPokemonAEntrenador(
            @Parameter(description = "UUID del entrenador") @PathVariable String uuid,
            @Parameter(description = "UUID del pokemon a agregar") @PathVariable String uuidPokemon) {
        try {
            Map<String, Object> response = pokemonService.agregarPokemonAEntrenador(uuid, uuidPokemon);
            // Si tiene error = true, retornamos 400, si no, 200
            if (Boolean.TRUE.equals(response.get("error"))) {
                return ResponseEntity.badRequest().body(response);
            }
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(Map.of("error", true, "message", e.getMessage()));
        }
    }
}
