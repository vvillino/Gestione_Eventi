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

    /*
     * Questo metodo permette di recuperare un Utente dal suo id
     * 
     * @return l'oggetto Utente
     * 
     * @param l'id dell'utente
     */
    @Override
    public Utente recuperaUno(long id) {

        Optional<Utente> u = utenteRepo.findById(id);
        return u.isEmpty() ? null : u.get();
    }

    /*
     * Questo metodo permette di salvare un Utente nel Database
     * 
     * @return valore booleano che identifica il successo o fallimento
     * dell'operazione
     * 
     * @param l'oggetto Utente da salvare
     */
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

    /*
     * Questo metodo permette di rimuovere un Utente dal Database
     * evitando che venga eliminato anche l'Evento ad esso associato
     * se presente all'interno della tabella intermedia dell'associazione
     * 
     * @return void
     * 
     * @param id dell'utente da eliminare
     */
    public void elimina(Long idUtente) {

        Utente utente = utenteRepo.findById(idUtente)
                .orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));

        for (Evento evento : utente.getEventi()) {

            evento.getUtenti().remove(utente);
            eventoRepo.save(evento);
        }

        // Elimina l'utente
        utenteRepo.delete(utente);
    }

    /*
     * Questo metodo permette di recuperare la lista degli Utenti iscritti
     * ad un determinato Evento
     * 
     * @return lista di utenti
     * 
     * @param l'id dell'utente
     */
    @Override
    public List<Utente> recuperaByEvento(Long id) {

        return utenteRepo.findAllByEvento(id);
    }

}
