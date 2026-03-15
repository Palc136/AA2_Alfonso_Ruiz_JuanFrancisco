/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import jakarta.persistence.*;
import java.time.LocalDateTime;
/**
 *
 * @author patch
 */
@Entity
public class Favorite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Movie pelicula;
    @Column(name = "fecha_agregado", insertable = false, updatable = false)
    private java.time.LocalDateTime fechaAgregado;
    
    public Favorite(){}
    
    public Favorite(Integer id, User usuario, Movie pelicula, LocalDateTime fechaAgregado) {
        this.id = id;
        this.usuario = usuario;
        this.pelicula = pelicula;
        this.fechaAgregado = fechaAgregado;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setPelicula(Movie pelicula) {
        this.pelicula = pelicula;
    }

    public void setFechaAgregado(LocalDateTime fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    public Integer getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public Movie getPelicula() {
        return pelicula;
    }

    public LocalDateTime getFechaAgregado() {
        return fechaAgregado;
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
        if (!(object instanceof Favorite)) {
            return false;
        }
        Favorite other = (Favorite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.juanalfonsomi.backend.model.Favorite[ id=" + id + " ]";
    }
    
}
