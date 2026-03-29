/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "generos")
public class Genere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose private Integer id;
    @Column(unique = true, nullable = false)
    @Expose private String nombre;
    @Expose private String descripcion;
    
    public Genere() {}

    public Genere(Integer id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
    
}
