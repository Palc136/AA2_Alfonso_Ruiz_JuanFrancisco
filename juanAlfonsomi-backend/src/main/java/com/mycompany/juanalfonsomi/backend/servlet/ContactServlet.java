/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.servlet;

import com.google.gson.Gson;
import com.mycompany.juanalfonsomi.backend.model.Contact;
import com.mycompany.juanalfonsomi.backend.service.ContactService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/api/contact")
public class ContactServlet extends HttpServlet {
    private final ContactService contactService = new ContactService();
    private final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // Procesar el mensaje de contacto
            Contact contact = gson.fromJson(request.getReader(), Contact.class);
            contactService.submitMessage(contact);
            
            response.setStatus(201);
            response.getWriter().print("{\"message\":\"Mensaje enviado con éxito\"}");
        } catch (Exception e) {
            response.setStatus(400);
            response.getWriter().print("{\"error\":\"" + e.getMessage() + "\"}");
        }
    }
}