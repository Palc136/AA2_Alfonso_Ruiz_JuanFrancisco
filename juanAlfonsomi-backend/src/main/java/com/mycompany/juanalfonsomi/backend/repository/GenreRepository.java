/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.Genere;
import java.util.List;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;

public class GenreRepository {
    public List<Genere> findAll() {
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.createQuery("SELECT g FROM Genre g", Genere.class).getResultList();
        }
    }
    
}
