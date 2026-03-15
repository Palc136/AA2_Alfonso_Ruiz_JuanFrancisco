/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.juanalfonsomi.backend.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    // Una sola conexión para toda la aplicación
    private static final EntityManagerFactory emf;

    // Se ejecuta cuando arranca Tomcat
    static {
        emf = Persistence.createEntityManagerFactory("miBackendPU");
    }

    // Cualquier clase puede pedir una conexión con este método
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    // Cierra la conexión cuando se apaga Tomcat
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}

