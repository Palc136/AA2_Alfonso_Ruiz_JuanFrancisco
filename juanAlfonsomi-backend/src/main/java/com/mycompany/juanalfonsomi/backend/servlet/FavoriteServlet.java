/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.servlet;

import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.Favorite;
import com.mycompany.juanalfonsomi.backend.service.FavoriteService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/favorites")
public class FavoriteServlet extends HttpServlet {
    private final FavoriteService favoriteService = new FavoriteService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Registramos una película como favorita para un usuario
            Favorite fav = gson.fromJson(request.getReader(), Favorite.class);
            favoriteService.toggleFavorite(fav);
            response.setStatus(200);
        } catch (Exception e) { response.setStatus(500); }
    }
}
