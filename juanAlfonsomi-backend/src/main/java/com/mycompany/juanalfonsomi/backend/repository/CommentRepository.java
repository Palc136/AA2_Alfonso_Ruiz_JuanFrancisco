/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.Comment;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;
/**
 *
 * @author patch
 */
public class CommentRepository {
    public void save(Comment comment) {
        var em = JPAUtil.getEntityManagerFactory().createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comment);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}
