package com.example.gestioneEventi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestioneEventi.model.Evento;
import com.example.gestioneEventi.model.Utente;
import com.example.gestioneEventi.repositories.EventoRepository;
import com.example.gestioneEventi.repositories.UtenteRepository;

@Service
public class PartecipantiService {

    @Autowired
    private EventoRepository eventoRepo;

    @Autowired
    private UtenteRepository utenteRepo;

    public boolean registrazioneUtente(Long idUtente, Long idEvento) {

        Optional<Utente> utenteDB = utenteRepo.findById(idUtente);
        Optional<Evento> eventoDB = eventoRepo.findById(idEvento);

        Utente utente = utenteDB.get();
        Evento evento = eventoDB.get();

        if (utente != null && evento != null) {
            utente.getEventi().add(evento);
            evento.getUtenti().add(utente);

            utenteRepo.save(utente);
            eventoRepo.save(evento);

            return true;
        }

        return false;

    }

    public List<Utente> getUtentyByEvento(Long idEvento) {

        Optional<Evento> eventoDB = eventoRepo.findById(idEvento);
        Evento evento = eventoDB.get();

        if (evento == null)
            return null;

        return evento.getUtenti();
    }

    public List<Evento> getEventiByUtente(Long idUtente) {

        Optional<Utente> utenteDB = utenteRepo.findById(idUtente);
        Utente utente = utenteDB.get();

        if (utente == null)
            return null;

        return utente.getEventi();
    }

}
