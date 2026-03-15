/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.User;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class UserRepository {
    
    public User findByEmail(String email) {
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                     .setParameter("email", email)
                     .getSingleResult();
        } catch (Exception e) { return null; }
    }

    // Guarda un usuario nuevo en MySQL
    public User save(User user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    // Busca todos los usuarios
    public List<User> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.createQuery("SELECT u FROM User u", User.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    // Busca un usuario por su ID
    public User findById(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    // Borra un usuario por su ID
    public void delete(Long id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    
}
/*

## ¿Qué hace cada método?
```
save()      → INSERT en MySQL, guarda usuario nuevo
findAll()   → SELECT * FROM users, trae todos
findById()  → SELECT * FROM users WHERE id = ?, busca uno
delete()    → DELETE FROM users WHERE id = ?, borra uno
```

## ¿Qué es EntityManager?

Es el objeto que Hibernate te da para hablar con MySQL. Cada operación abre una conexión, hace su trabajo y la cierra con `em.close()`.

## ¿Qué es una Transaction?
```
em.getTransaction().begin()    → "voy a hacer cambios en la BD"
em.getTransaction().commit()   → "confirma los cambios"
em.getTransaction().rollback() → "algo falló, deshaz todo"
*/