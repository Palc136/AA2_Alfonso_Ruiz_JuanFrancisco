/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.Contact;
import com.mycompany.juanalfonsomi.backend.repository.ContactRepository;

public class ContactService {
    private final ContactRepository contactRepository = new ContactRepository();

    public void submitMessage(Contact contact) {
        // REGLA: Obligamos a que el mensaje tenga un propósito
        if (contact.getMensaje() == null || contact.getMensaje().length() < 10) {
            throw new IllegalArgumentException("Por favor, describe mejor tu consulta.");
        }
        // REGLA: Marcamos el mensaje como 'pendiente' por defecto
        contact.setEstado(Contact.ContactStatus.pendiente);
        
        contactRepository.save(contact);
    }
}
