/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacto")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id") // Puede ser NULL si el usuario no está logueado
    private User usuario;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_envio", insertable = false, updatable = false)
    private LocalDateTime fechaEnvio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactStatus estado = ContactStatus.pendiente;

    public enum ContactStatus {
        pendiente, leido, respondido
    }
    
    public Contact(){}
    // Getters y Setters...

    public Contact(Integer id, User usuario, String nombre, String email, String mensaje, LocalDateTime fechaEnvio) {
        this.id = id;
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.mensaje = mensaje;
        this.fechaEnvio = fechaEnvio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public void setEstado(ContactStatus estado) {
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public User getUsuario() {
        return usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public ContactStatus getEstado() {
        return estado;
    }
    
    
    
    
}
