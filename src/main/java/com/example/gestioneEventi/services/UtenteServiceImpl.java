package com.example.gestioneEventi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestioneEventi.model.Evento;
import com.example.gestioneEventi.model.Utente;
import com.example.gestioneEventi.repositories.EventoRepository;
import com.example.gestioneEventi.repositories.UtenteRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepo;

    @Autowired
    private EventoRepository eventoRepo;

    @Override
    public Utente recuperaUno(long id) {

        Optional<Utente> u = utenteRepo.findById(id);
        return u.isEmpty() ? null : u.get();
    }

    @Override
    public Boolean salva(Utente utente) {
        boolean esito = true;

        try {
            utenteRepo.save(utente);
        } catch (Exception e) {
            esito = false;
        }

        return esito;
    }

    // @Override
    // public void elimina(Long id) {
    // utenteRepo.deleteById(id);
    // }

    public void elimina(Long userId) {

        Utente utente = utenteRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        for (Evento evento : utente.getEventi()) {

            evento.getUtenti().remove(utente);
            eventoRepo.save(evento);
        }

        // Elimina l'utente
        utenteRepo.delete(utente);
    }

    @Override
    public List<Utente> recuperaByEvento(Long id) {

        return utenteRepo.findAllByEvento(id);
    }

}
