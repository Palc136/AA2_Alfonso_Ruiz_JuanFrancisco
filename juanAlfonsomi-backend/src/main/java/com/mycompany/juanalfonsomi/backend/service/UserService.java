/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.User;
import com.mycompany.juanalfonsomi.backend.repository.UserRepository;
import java.util.List;

public class UserService {

    // El service usa el repository para acceder a la BD
    private final UserRepository userRepository = new UserRepository();

    // Obtener todos los usuarios
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtener un usuario por ID
    public User getUserById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID no válido");
        }
        return userRepository.findById(id);
    }
    
    // Lógica de inicio de sesión
    public User login(String email, String password) {
        // REGLA: Email y password son obligatorios para intentar loguearse
        if (email == null || password == null) return null;

        User user = userRepository.findByEmail(email);
        
        // REGLA: Verificamos si el usuario existe y si la clave coincide
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Crear un usuario nuevo
    public User createUser(String name, String email) {
        // Regla de negocio: nombre y email son obligatorios
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        // Regla de negocio: el email debe tener formato válido
        if (!email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido");
        }

        User user = new User(name.trim(), email.trim());
        return userRepository.save(user);
    }

    // Eliminar un usuario
    public void deleteUser(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID no válido");
        }
        userRepository.delete(id);
        
        
    }
}
/*
## ¿Por qué existe el Service si el Repository ya hace todo?

Muy buena pregunta que seguro te estás haciendo. Mira la diferencia:
```
Repository  →  solo sabe hablar con la BD
               no sabe si un email es válido
               no sabe si un nombre es obligatorio
               solo guarda y consulta

Service     →  sabe las REGLAS de la aplicación
               valida que el email tenga @
               valida que el nombre no esté vacío
               protege la BD de datos incorrectos*/
