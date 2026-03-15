/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.Favorite;
import com.mycompany.juanalfonsomi.backend.repository.FavoriteRepository;
import java.util.List;

public class FavoriteService {
    private final FavoriteRepository favoriteRepository = new FavoriteRepository();

    public void toggleFavorite(Favorite favorite) {
        // REGLA: Verificamos que existan ambos IDs (usuario y película)
        if (favorite.getUsuario() == null || favorite.getPelicula() == null) {
            throw new IllegalArgumentException("Error al procesar el favorito.");
        }
        favoriteRepository.addFavorite(favorite);
    }

    public List<Favorite> getUserFavorites(Integer userId) {
        return favoriteRepository.findByUser(userId);
    }
}
