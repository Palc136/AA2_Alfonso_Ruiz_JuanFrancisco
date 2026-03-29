/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.model;

import com.google.gson.annotations.Expose;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author patch
 */
@Entity
@Table(name = "reparto_pelicula")
public class MovieCast implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose private Integer id;
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    @Expose private Movie pelicula;
    // 
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "actor_id") // Nombre de la columna 
@Expose private Actor actor;
@Expose private String personaje;
    @Column(name = "orden_aparicion")
    @Expose  private Integer ordenAparicion;
    public MovieCast(){}
    public MovieCast(Integer id, Movie pelicula, Actor actor, String personaje, Integer ordenAparicion) {
        this.id = id;
        this.pelicula = pelicula;
        this.actor = actor;
        this.personaje = personaje;
        this.ordenAparicion = ordenAparicion;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPelicula(Movie pelicula) {
        this.pelicula = pelicula;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setPersonaje(String personaje) {
        this.personaje = personaje;
    }

    public void setOrdenAparicion(Integer ordenAparicion) {
        this.ordenAparicion = ordenAparicion;
    }

    public Integer getId() {
        return id;
    }

    public Movie getPelicula() {
        return pelicula;
    }

    public Actor getActor() {
        return actor;
    }

    public String getPersonaje() {
        return personaje;
    }

    public Integer getOrdenAparicion() {
        return ordenAparicion;
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
        if (!(object instanceof MovieCast)) {
            return false;
        }
        MovieCast other = (MovieCast) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.juanalfonsomi.backend.model.MovieCast[ id=" + id + " ]";
    }
    
}
