/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.servlet;

import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.User;
import com.mycompany.juanalfonsomi.backend.service.UserService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/api/users")  // Tomcat escucha en esta URL
public class UserServlet extends HttpServlet {

    private final UserService userService = new UserService();
    private final Gson gson = new Gson();

    // GET /api/users → trae todos los usuarios
    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            List<User> users = userService.getAllUsers();
            out.print(gson.toJson(users));
        } catch (Exception e) {
            response.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // POST /api/users → crea un usuario nuevo
    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // Lee los datos que manda el frontend
            String name  = request.getParameter("name");
            String email = request.getParameter("email");

            User newUser = userService.createUser(name, email);
            response.setStatus(201); // 201 = creado exitosamente
            out.print(gson.toJson(newUser));
        } catch (IllegalArgumentException e) {
            response.setStatus(400); // 400 = datos incorrectos
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        } catch (Exception e) {
            response.setStatus(500); // 500 = error del servidor
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }

    // DELETE /api/users?id=1 → borra un usuario
    @Override
    protected void doDelete(HttpServletRequest request, 
                            HttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            Long id = Long.valueOf(request.getParameter("id"));
            userService.deleteUser(id);
            response.setStatus(200);
            out.print("{\"message\":\"Usuario eliminado\"}");
        } catch (NumberFormatException e) {
            response.setStatus(500);
            out.print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}
/*
## ¿Qué hace cada método?
```
doGet()    → responde a GET /api/users
             devuelve lista de usuarios en JSON

doPost()   → responde a POST /api/users
             recibe name y email, crea usuario nuevo

doDelete() → responde a DELETE /api/users?id=1
             borra el usuario con ese ID
```

## ¿Qué son los códigos de estado HTTP?
```
200 → OK, todo bien
201 → Created, se creó algo nuevo
400 → Bad Request, datos incorrectos del cliente
500 → Internal Server Error, algo falló en el servidor*/