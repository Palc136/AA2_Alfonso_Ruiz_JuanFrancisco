/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.Movie;
import com.mycompany.juanalfonsomi.backend.repository.MovieRepository;
import java.util.List;

public class MovieService {
    // Definimos el repositorio para acceder a la tabla 'peliculas'
    private final MovieRepository movieRepository = new MovieRepository();

    // Obtener catálogo completo
    public List<Movie> getAllMovies() {
        // Simplemente solicita todas las películas al repositorio
        return movieRepository.findAll();
    }

    // Obtener detalle de una película
    public Movie getMovieById(Integer id) {
        // REGLA: El ID debe ser un número positivo coherente
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("El ID de la película no es válido");
        }
        // Si es válido, lo buscamos en la base de datos
        return movieRepository.findById(id);
    }
}
    

