/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.repository;

import com.mycompany.juanalfonsomi.backend.model.Contact;
import com.mycompany.juanalfonsomi.backend.util.JPAUtil;

public class ContactRepository {
    
    public void save(Contact contact) {
        try (jakarta.persistence.EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager()) {
            em.getTransaction().begin();
            em.persist(contact);
            em.getTransaction().commit();
        }
    }
    
    
}
