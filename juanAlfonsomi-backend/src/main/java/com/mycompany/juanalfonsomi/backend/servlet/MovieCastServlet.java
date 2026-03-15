/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.servlet;

import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.MovieCast;
import com.mycompany.juanalfonsomi.backend.service.MovieCastService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/cast")
public class MovieCastServlet extends HttpServlet {
    private final MovieCastService castService = new MovieCastService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            // Obtenemos el reparto filtrando por la película seleccionada
            Integer movieId = Integer.valueOf(request.getParameter("movieId"));
            List<MovieCast> castList = castService.getMovieCast(movieId);
            response.getWriter().print(gson.toJson(castList));
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().print("{\"error\":\"ID de película requerido\"}");
        }
    }
}
