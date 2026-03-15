/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.servlet;

import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.Comment;
import com.mycompany.juanalfonsomi.backend.service.CommentService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/comments")
public class CommentServlet extends HttpServlet {
    private final CommentService commentService = new CommentService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        try {
            // Transformamos el JSON de la reseña en un objeto Comment
            Comment comment = gson.fromJson(request.getReader(), Comment.class);
            commentService.addComment(comment);
            
            response.setStatus(201); // Éxito en la creación
            response.getWriter().print("{\"status\":\"Comentario publicado\"}");
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().print("{\"error\":\"No se pudo publicar el comentario\"}");
        }
    }
}
