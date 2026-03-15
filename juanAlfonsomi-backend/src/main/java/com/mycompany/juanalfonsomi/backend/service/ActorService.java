/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juanalfonsomi.backend.service;

import com.mycompany.juanalfonsomi.backend.model.Actor;
import com.mycompany.juanalfonsomi.backend.repository.ActorRepository;

public class ActorService {
    private final ActorRepository actorRepository = new ActorRepository();

    public Actor getActorById(Integer id) {
        // REGLA: Validación de integridad del ID
        if (id == null || id <= 0) throw new IllegalArgumentException("ID inválido.");
        return actorRepository.findById(id);
    }
}
