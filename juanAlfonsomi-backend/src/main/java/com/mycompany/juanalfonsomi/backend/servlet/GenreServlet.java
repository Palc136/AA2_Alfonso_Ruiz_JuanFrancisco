package com.mycompany.juanalfonsomi.backend.servlet;


import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.Genere;
import com.mycompany.juanalfonsomi.backend.service.GenereService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
@WebServlet("/api/genres")
public class GenreServlet extends HttpServlet {
    private final GenereService GenereService = new GenereService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        // Traemos todas las categorías definidas en la tabla 'generos'
        List<Genere> genres = GenereService.getAllGenres();
        response.getWriter().print(gson.toJson(genres));
    }
}
