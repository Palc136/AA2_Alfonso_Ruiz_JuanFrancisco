/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.Favorite;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;


public class FavoriteRepository {
    public void addFavorite(Favorite fav) {
        var em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(fav);
            em.getTransaction().commit();
        } finally { em.close(); }
    }
    
    /**
     * Obtiene la lista de películas favoritas de un usuario específico.
     * @param userId El ID del usuario que inició sesión.
     * @return Lista de objetos Favorite (relación usuario-película).
     */
    public List<Favorite> findByUser(Integer userId) {
        // 1. Creamos la sesión de trabajo con la base de datos
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        
        try {
            // 2. Definimos una consulta JPQL para filtrar por el ID del usuario
            // 'f.usuario.id' accede al ID de la entidad User relacionada con el Favorito
            return em.createQuery("SELECT f FROM Favorite f WHERE f.usuario.id = :uid", Favorite.class)
                     // 3. Inyectamos el ID del usuario de forma segura (PreparedStatement interno)
                     .setParameter("uid", userId)
                     // 4. Ejecutamos y convertimos las filas de la DB en una lista de objetos Java
                     .getResultList();
        } finally {
            // 5. Liberamos el recurso de conexión
            em.close();
        }
    }
    
}
