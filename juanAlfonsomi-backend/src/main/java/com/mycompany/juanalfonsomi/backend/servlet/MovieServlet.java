/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package com.mycompany.juanalfonsomi.backend.servlet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken; // Necesario para leer listas de JSON
import com.mycompany.juanalfonsomi.backend.model.Movie;
import com.mycompany.juanalfonsomi.backend.model.MovieCast;
import com.mycompany.juanalfonsomi.backend.service.MovieService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;



@WebServlet(name = "MovieServlet", urlPatterns = {"/api/movies"})
public class MovieServlet extends HttpServlet {

    private final MovieService movieService = new MovieService();
    
    private final Gson gson = new GsonBuilder()
    .excludeFieldsWithoutExposeAnnotation() // Crucial: solo envía lo que tiene @Expose
    .setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            // Evita bucle infinito: No vuelvas a Película desde MovieCast
            if (f.getName().equals("pelicula") && f.getDeclaringClass().equals(MovieCast.class)) {
                return true;
            }
            // Evita error de accesibilidad con fechas
            return f.getDeclaredClass().equals(java.time.LocalDate.class);
        }
        @Override
        public boolean shouldSkipClass(Class<?> clazz) { return false; }
    })
    .create();
    
    

    // GET: Para que Angular lea las películas
    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
   response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();
        
    try {
        String idStr = request.getParameter("id");

        if (idStr != null) {
            // Caso: Buscar una sola película por ID
            Integer id = Integer.valueOf(idStr);
            Movie movie = movieService.getMovieById(id);
            if (movie != null) {
                out.print(this.gson.toJson(movie));
            } else {
                response.setStatus(404);
                out.print("{\"error\":\"Pelicula no encontrada\"}");
            }
        } else {
            // Caso: Listar todas las películas
            List<Movie> movies = movieService.getAllMovies();
            out.print(this.gson.toJson(movies));
        }
        
        // Empujamos los datos
        out.flush();

    } catch (NumberFormatException e) {
        response.setStatus(400);
        out.print("{\"error\":\"ID invalido\"}");
    } catch (Exception e) {
        response.setStatus(500);
        out.print("{\"error\":\"" + e.getMessage() + "\"}");
        e.printStackTrace(); // Esto te ayudará a ver el error real en NetBeans
    } finally {
        // Cerramos el flujo siempre, pase lo que pase
        if (out != null) {
            out.close();
        }
    }
}

    // POST: Este método recibirá la migración desde Angular
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 1. Leer el cuerpo del JSON que envía Angular
            StringBuilder buffer = new StringBuilder();
            BufferedReader reader = request.getReader();
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            String payload = buffer.toString();

            // 2. Convertir el JSON a una lista de objetos Movie
            Type listType = new TypeToken<List<Movie>>(){}.getType();
            List<Movie> movies = gson.fromJson(payload, listType);

            // 3. Guardar cada película usando tu servicio
            for (Movie m : movies) {
                movieService.saveMovie(m); 
            }

            out.print("{\"message\":\"Migración exitosa: " + movies.size() + " películas guardadas.\"}");
            
        } catch (Exception e) {
            response.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
    
}

