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
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "pelicula_id")
    private Movie pelicula;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    private Integer calificacion; // 1-5
    @Column(nullable = false, columnDefinition = "TEXT")
    private String texto;
    @Column(name = "fecha_comentario", insertable = false, updatable = false)
    private java.time.LocalDateTime fechaComentario;
    
    public Comment(){}

    public Comment(Integer id, Movie pelicula, User usuario, Integer calificacion, String texto, LocalDateTime fechaComentario) {
        this.id = id;
        this.pelicula = pelicula;
        this.usuario = usuario;
        this.calificacion = calificacion;
        this.texto = texto;
        this.fechaComentario = fechaComentario;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPelicula(Movie pelicula) {
        this.pelicula = pelicula;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFechaComentario(LocalDateTime fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public Integer getId() {
        return id;
    }

    public Movie getPelicula() {
        return pelicula;
    }

    public User getUsuario() {
        return usuario;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public String getTexto() {
        return texto;
    }

    public LocalDateTime getFechaComentario() {
        return fechaComentario;
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
        if (!(object instanceof Comment)) {
            return false;
        }
        Comment other = (Comment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.juanalfonsomi.backend.model.Comment[ id=" + id + " ]";
    }
    
}
