/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity                    // le dice a Hibernate: "esto es una tabla"
@Table(name = "users")     // el nombre de la tabla en MySQL
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String email;
    private String password;
    @Column(name = "fecha_registro", insertable = false, updatable = false)
    private java.time.LocalDateTime fechaRegistro;
    private Boolean activo = true;

    // Constructor vacío obligatorio para JPA
    public User(String trim, String trim1) {}
    
 // Constructor con datos
    public User(Integer id, String nombre, String email, String password, LocalDateTime fechaRegistro,Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.fechaRegistro = fechaRegistro;
        this.activo= activo;
    }
    
    
    
    

   
   

    // Getters y Setters

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
}


/*

## ¿Qué hace cada anotación?
CREATE TABLE users (
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
```
@Entity          → esta clase = una tabla en MySQL
@Table           → define el nombre exacto de la tabla
@Id              → este campo es la clave primaria (PK)
@GeneratedValue  → MySQL genera el ID automáticamente (autoincrement)
@Column          → define propiedades de cada columna*/
