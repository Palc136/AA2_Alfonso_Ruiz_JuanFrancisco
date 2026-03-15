/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.MovieCast;
import com.mycompany.juanalfonsomi.backend.repository.MovieCastRepository;
import java.util.List;

public class MovieCastService {
    private final MovieCastRepository castRepository = new MovieCastRepository();

    public List<MovieCast> getMovieCast(Integer movieId) {
        // REGLA: El ID de película debe ser válido para buscar su reparto
        if (movieId == null || movieId <= 0) {
            throw new IllegalArgumentException("ID de película no válido.");
        }
        return castRepository.findByMovieId(movieId);
    }
}
