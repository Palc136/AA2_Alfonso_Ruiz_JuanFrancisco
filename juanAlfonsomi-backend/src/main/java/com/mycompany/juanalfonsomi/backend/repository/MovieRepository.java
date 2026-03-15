/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.Movie;
import java.util.List;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;
import jakarta.persistence.EntityManager;

/**
 *
 * @author patch
 */
public class MovieRepository {
    public List<Movie> findAll() {
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
        }
    }
    
    /**
     * Busca una película específica por su Llave Primaria (ID).
     * @param id El identificador único de la película en la tabla 'peliculas'.
     * @return El objeto Movie encontrado o null si no existe.
     */
    public Movie findById(Integer id) {
        // 1. Abrir una conexión (EntityManager) usando  clase de utilidad
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        try {
            // 2. Usr el método .find() de JPA, que es el más eficiente para buscar por ID
            // JPA busca automáticamente en la tabla mapeada por la clase Movie
            return em.find(Movie.class, id);
        } finally {
            // 3. Cerramos la conexión SIEMPRE para no agotar la memoria de Tomcat
            em.close();
        }
    }
    
}
