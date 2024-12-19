package com.example.gestioneEventi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestioneEventi.model.Evento;
import com.example.gestioneEventi.model.Utente;
import com.example.gestioneEventi.services.PartecipantiService;

@RestController
@RequestMapping("/registrazione")
public class PartecipantiController {

    @Autowired
    private PartecipantiService partecipantiService;

    /*
     * Richiesta POST per registrare un Utente ad un evento specifico
     * inserendo nell'URL l'id sia dell'evento e successivamente quello
     * dell'utente su cui effettuare l'operazione.
     * 
     * Se l'operazione ha successo restituisce un HTTP Status 200 OK,
     * altrimenti restituisce un 404 Not Found
     * 
     * Link d'esempio per la rotta:
     * 
     * http://localhost:8080/gestione_eventi/registrazione/2/3
     */
    @PostMapping("/{idEvento}/{idUtente}")
    public ResponseEntity<String> registraUtente(@PathVariable Long idEvento, @PathVariable Long idUtente) {

        if (partecipantiService.registrazioneUtente(idUtente, idEvento))
            return ResponseEntity.status(HttpStatus.OK).body("Ti sei registrato con successo all'evento.");
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Registrazione fallita.");

    }

    /*
     * Richiesta GET per visualizzare una lista di utenti iscritti ad
     * uno specifico evento. Se l'operazione riesce fornisce uno status
     * HTTP 200 OK, altrimenti se la lista è vuota restituisce un 204 No Content
     * 
     * Link d'esempio per la rotta:
     * 
     * http://localhost:8080/gestione_eventi/registrazione/2/utenti
     */
    @GetMapping("/{idEvento}/utenti")
    public ResponseEntity<List<Utente>> getUtentiRegistratiByEvento(@PathVariable Long idEvento) {

        List<Utente> utenti = partecipantiService.getUtentyByEvento(idEvento);

        if (utenti.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(utenti);

    }

    /*
     * Richiesta GET per visualizzare una lista di eventi a cui un determinato
     * utente si è registrato. Se l'operazione riesce fornisce uno status
     * HTTP 200 OK, altrimenti se la lista è vuota restituisce un 204 No Content
     * 
     * Link d'esempio per la rotta:
     * 
     * http://localhost:8080/gestione_eventi/registrazione/2/eventi
     */
    @GetMapping("/{idUtente}/eventi")
    public ResponseEntity<List<Evento>> getEventiByUtente(@PathVariable Long idUtente) {

        List<Evento> eventi = partecipantiService.getEventiByUtente(idUtente);

        if (eventi.isEmpty())
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        else
            return ResponseEntity.status(HttpStatus.OK).body(eventi);

    }

}
