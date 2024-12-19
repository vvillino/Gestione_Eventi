package com.example.gestioneEventi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestioneEventi.services.PartecipantiService;

@RestController
@RequestMapping("/registrazione")
public class PartecipantiController {

    @Autowired
    private PartecipantiService partecipantiService;

    // http://localhost:8080/gestione_eventi/registrazione/2/3
    @PostMapping("/{idEvento}/{idUtente}")
    public ResponseEntity<String> registraUtente(@PathVariable Long idEvento, @PathVariable Long idUtente) {

        if (partecipantiService.registrazioneUtente(idUtente, idEvento))
            return ResponseEntity.status(HttpStatus.OK).body("Ti sei registrato con successo all'evento.");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registrazione fallita.");

    }

}
