package com.examen.pokemon.services;

import com.examen.pokemon.dto.PokemonRequest;
import com.examen.pokemon.models.Entrenador;
import com.examen.pokemon.models.Pokemon;
import com.examen.pokemon.models.TipoPokemon;
import com.examen.pokemon.repositories.EntrenadorRepository;
import com.examen.pokemon.repositories.PokemonRepository;
import com.examen.pokemon.repositories.TipoPokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Servicio que contiene toda la logica de negocio de Pokemon.
 * El controlador llama a este servicio para hacer el trabajo real.
 */
@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TipoPokemonRepository tipoPokemonRepository;

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    /**
     * Servicio 2: Listar todos los pokemones de un tipo especifico.
     * El 'tipo' es el UUID del TipoPokemon.
     */
    public List<Pokemon> listarPokemonesPorTipo(String tipoUuid) {
        TipoPokemon tipo = tipoPokemonRepository.findByUuid(tipoUuid)
                .orElseThrow(() -> new RuntimeException("Tipo de pokemon no encontrado con UUID: " + tipoUuid));
        return pokemonRepository.findByTipoPokemon(tipo);
    }

    /**
     * Servicio 3: Registrar un nuevo Pokemon en el sistema.
     */
    @Transactional
    public Pokemon registrarPokemon(PokemonRequest request) {
        TipoPokemon tipo = tipoPokemonRepository.findByUuid(request.getTipoPokemon())
                .orElseThrow(() -> new RuntimeException("Tipo de pokemon no encontrado con UUID: " + request.getTipoPokemon()));

        Pokemon pokemon = new Pokemon();
        pokemon.setNombre(request.getNombre());
        pokemon.setDescripcion(request.getDescripcion());
        pokemon.setFechaDescubrimiento(request.getFechaDescubrimiento());
        pokemon.setGeneracion(request.getGeneracion());
        pokemon.setTipoPokemon(tipo);

        return pokemonRepository.save(pokemon);
    }

    /**
     * Servicio 4: Listar todos los pokemones de un entrenador especifico.
     */
    public List<Pokemon> listarPokemonesDeEntrenador(String entrenadorUuid) {
        Entrenador entrenador = entrenadorRepository.findByUuid(entrenadorUuid)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con UUID: " + entrenadorUuid));
        return entrenador.getPokemones();
    }

    /**
     * Servicio 5: Agregar un pokemon al listado de pokemones de un entrenador.
     * Si el pokemon ya esta registrado al entrenador, retorna un mensaje de error.
     */
    @Transactional
    public Map<String, Object> agregarPokemonAEntrenador(String entrenadorUuid, String pokemonUuid) {
        Map<String, Object> response = new HashMap<>();

        Entrenador entrenador = entrenadorRepository.findByUuid(entrenadorUuid)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con UUID: " + entrenadorUuid));

        Pokemon pokemon = pokemonRepository.findByUuid(pokemonUuid)
                .orElseThrow(() -> new RuntimeException("Pokemon no encontrado con UUID: " + pokemonUuid));

        // Verificamos si el pokemon ya esta asociado al entrenador
        boolean yaRegistrado = entrenador.getPokemones().stream()
                .anyMatch(p -> p.getUuid().equals(pokemonUuid));

        if (yaRegistrado) {
            // Si ya esta, retornamos el mensaje de error que pide el examen
            response.put("error", true);
            response.put("message", "pokemon ya esta registrado al entrenador");
            return response;
        }

        // Si no esta, lo agregamos
        entrenador.getPokemones().add(pokemon);
        entrenadorRepository.save(entrenador);

        // Retornamos el listado actualizado de pokemones del entrenador
        response.put("error", false);
        response.put("pokemones", entrenador.getPokemones());
        return response;
    }
}
