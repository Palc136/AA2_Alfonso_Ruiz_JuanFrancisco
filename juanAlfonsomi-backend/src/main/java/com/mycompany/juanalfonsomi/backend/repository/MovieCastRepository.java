/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.MovieCast;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 * Clase encargada de gestionar las operaciones de persistencia para la tabla 'reparto_pelicula'.
 */
public class MovieCastRepository {

    /**
     * Obtiene el reparto completo de una película específica.
     * @param movieId El ID de la película a consultar.
     * @return Lista de objetos MovieCast (Actores y sus personajes).
     */
    public List<MovieCast> findByMovieId(Integer movieId) {
        // 1. Creamos el EntityManager para iniciar la comunicación con la base de datos cineplus_db
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        try {
            // 2. Definimos una consulta JPQL para filtrar el reparto por el ID de la película
            // 'mc.pelicula.id' hace referencia a la relación mapeada en la entidad MovieCast
            return em.createQuery("SELECT mc FROM MovieCast mc WHERE mc.pelicula.id = :mid", MovieCast.class)
                     // 3. Asignamos el valor del parámetro 'mid' de forma segura para evitar inyección SQL
                     .setParameter("mid", movieId)
                     // 4. Ejecutamos la consulta y obtenemos la lista de resultados
                     .getResultList();
        } finally {
            // 5. Cerramos el EntityManager para liberar los recursos del servidor Tomcat
            em.close();
        }
    }

    /**
     * Guarda una nueva relación de reparto (Actor-Película-Personaje) en la base de datos.
     * @param cast
     */
    public void save(MovieCast cast) {
        // 1. Solicitamos una nueva conexión a través de la fábrica de entidades definida en JPAUtil
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        try {
            // 2. Iniciamos una transacción: esto es obligatorio para operaciones de escritura (INSERT, UPDATE, DELETE)
            em.getTransaction().begin();
            
            // 3. El método persist toma el objeto Java y lo prepara para ser una nueva fila en 'reparto_pelicula'
            em.persist(cast);
            
            // 4. Confirmamos los cambios (commit) para que se graben permanentemente en MySQL
            em.getTransaction().commit();
        } catch (Exception e) {
            // 5. Si algo falla, revertimos los cambios para no dejar datos corruptos
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e; // Relanzamos la excepción para que el Servlet pueda manejar el error
        } finally {
            // 6. Siempre cerramos la conexión, ocurra o no un error
            em.close();
        }
    }
}
