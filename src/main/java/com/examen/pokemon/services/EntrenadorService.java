package com.examen.pokemon.services;

import com.examen.pokemon.models.Entrenador;
import com.examen.pokemon.repositories.EntrenadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Servicio que contiene la logica de negocio relacionada al Entrenador.
 */
@Service
public class EntrenadorService {

    @Autowired
    private EntrenadorRepository entrenadorRepository;

    /**
     * Servicio 1: Login del entrenador.
     * Recibe un email y retorna el UUID del entrenador si lo encuentra.
     */
    public Map<String, String> login(String email) {
        Map<String, String> response = new HashMap<>();

        Entrenador entrenador = entrenadorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Entrenador no encontrado con email: " + email));

        response.put("uid", entrenador.getUuid());
        return response;
    }
}
