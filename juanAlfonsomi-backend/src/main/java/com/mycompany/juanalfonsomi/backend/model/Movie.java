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
import static java.rmi.server.RemoteRef.serialVersionUID;
import java.time.LocalDateTime;
import java.util.List;
import com.mycompany.juanalfonsomi.backend.model.Comment; 

/**
 *
 * @author patch
 */
@Entity
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private Integer anio;
    @ManyToOne
    @JoinColumn(name = "genero_id")
    private Genere genero;
    private Double rating;
    @Column(name = "imagen_portada")
    private String imagenPortada;
    @Column(columnDefinition = "TEXT")
    private String sinopsis;

    // Relación para traer los comentarios
    @OneToMany(mappedBy = "pelicula")
    private List<Comment> comentarios;
   
    
    public Movie() {}

    public Movie(Integer id, String titulo, Integer anio, Genere genero, Double rating, String imagenPortada, String sinopsis, List<Comment> comentarios) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.genero = genero;
        this.rating = rating;
        this.imagenPortada = imagenPortada;
        this.sinopsis = sinopsis;
        this.comentarios = comentarios;
    }

   

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   
    public void setGenero(Genere genero) {
        this.genero = genero;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public void setImagenPortada(String imagenPortada) {
        this.imagenPortada = imagenPortada;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setComentarios(List<Comment> comentarios) {
        this.comentarios = comentarios;
    }

    
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitulo() {
        return titulo;
    }

   

    public Genere getGenero() {
        return genero;
    }

    public Double getRating() {
        return rating;
    }

    public String getImagenPortada() {
        return imagenPortada;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public List<Comment> getComentarios() {
        return comentarios;
    }

    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Movie)) {
            return false;
        }
        Movie other = (Movie) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.juanalfonsomi.backend.model.Movies[ id=" + id + " ]";
    }
    
}
