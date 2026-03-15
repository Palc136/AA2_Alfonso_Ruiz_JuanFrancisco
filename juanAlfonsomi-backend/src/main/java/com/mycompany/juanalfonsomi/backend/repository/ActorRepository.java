/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.Actor;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;


public class ActorRepository {
    public Actor findById(Integer id) {
        try (var em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            return em.find(Actor.class, id);
        }
    }
    
}
