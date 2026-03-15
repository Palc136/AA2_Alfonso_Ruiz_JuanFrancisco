/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author patch
 */
@Entity
public class Actor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String imagen;
    @Column(columnDefinition = "TEXT")
    private String biografia;
    @Column(name = "fecha_nacimiento")
    private java.time.LocalDate fechaNacimiento;
    // Getters y Setters...

    public Actor(){}
    public Actor(Integer id, String nombre, String imagen, String biografia, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.imagen = imagen;
        this.biografia = biografia;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.juanalfonsomi.backend.model.Actor[ id=" + id + " ]";
    }
    
}
