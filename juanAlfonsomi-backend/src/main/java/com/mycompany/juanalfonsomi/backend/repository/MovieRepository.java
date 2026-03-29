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
    public void save(Movie movie) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin(); // Iniciamos la transacción para escribir
            
            // persist() toma  objeto Movie y lo guarda automáticamente en la tabla
            em.persist(movie); 
            
            em.getTransaction().commit(); // Confirmamos los cambios en MySQL
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Si algo falla, cancelamos para no romper la DB
            }
            System.err.println("Error al guardar la película: " + e.getMessage());
        } finally {
            em.close(); // Siempre cerramos la conexión
        }
    }
    
}
