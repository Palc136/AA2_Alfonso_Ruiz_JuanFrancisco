package com.mycompany.juanalfonsomi.backend.servlet;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.Actor;
import com.mycompany.juanalfonsomi.backend.service.ActorService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/actors")
public class ActorServlet extends HttpServlet {
    private final ActorService actorService = new ActorService();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            // Buscamos al actor por el ID enviado desde Angular
            Integer id = Integer.valueOf(request.getParameter("id"));
            Actor actor = actorService.getActorById(id);
            response.getWriter().print(gson.toJson(actor));
        } catch (Exception e) {
            response.setStatus(404); // Not Found si el actor no existe
        }
    }
}
