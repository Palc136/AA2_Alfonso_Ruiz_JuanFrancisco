/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.Genere;
import com.mycompany.juanalfonsomi.backend.repository.GenreRepository;
import java.util.List;

public class GenereService {
    private final GenreRepository genreRepository = new GenreRepository();

    public List<Genere> getAllGenres() {
        // Simplemente devuelve todas las categorías disponibles
        return genreRepository.findAll();
    }
}