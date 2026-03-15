/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.juanalfonsomi.backend.servlet;

import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.Movie;
import com.mycompany.juanalfonsomi.backend.service.MovieService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "MovieServlet", urlPatterns = {"/api/movies"}) // URL donde escucha Tomcat
public class MovieServlet extends HttpServlet {

    private final MovieService movieService = new MovieService();
    private final Gson gson = new Gson();

    // GET /api/movies?id=1 -> Detalle | GET /api/movies -> Lista completa
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Configuramos la respuesta para que el navegador sepa que enviamos JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            String idStr = request.getParameter("id"); // Obtenemos el ID de la URL si existe
            
            if (idStr != null) {
                // Caso: Buscar una película específica
                Integer id = Integer.valueOf(idStr);
                Movie movie = movieService.getMovieById(id);
                out.print(gson.toJson(movie)); // Transformamos el objeto a JSON
            } else {
                // Caso: Listar todo el catálogo
                List<Movie> movies = movieService.getAllMovies();
                out.print(gson.toJson(movies));
            }
        } catch (Exception e) {
            response.setStatus(500); // Error interno del servidor
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
