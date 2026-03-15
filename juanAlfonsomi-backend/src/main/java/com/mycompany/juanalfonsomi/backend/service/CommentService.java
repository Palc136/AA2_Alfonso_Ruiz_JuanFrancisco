/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.Comment;
import com.mycompany.juanalfonsomi.backend.repository.CommentRepository;

public class CommentService {
    private final CommentRepository commentRepository = new CommentRepository();

    public void addComment(Comment comment) {
        // REGLA: El comentario debe tener texto sustancial
        if (comment.getTexto() == null || comment.getTexto().trim().length() < 5) {
            throw new IllegalArgumentException("El comentario es demasiado corto.");
        }
        // REGLA: La calificación debe estar entre 1 y 5 estrellas
        if (comment.getCalificacion() < 1 || comment.getCalificacion() > 5) {
            throw new IllegalArgumentException("La calificación debe ser de 1 a 5 estrellas.");
        }
        commentRepository.save(comment);
    }
}