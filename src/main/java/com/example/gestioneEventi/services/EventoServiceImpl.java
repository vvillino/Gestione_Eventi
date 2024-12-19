package com.example.gestioneEventi.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestioneEventi.model.Evento;
import com.example.gestioneEventi.repositories.EventoRepository;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepo;

    /*
     * Questo metodo permette di recuperare la lista degli eventi
     * 
     * @return lista di eventi
     */
    @Override
    public List<Evento> recuperaTutti() {

        return eventoRepo.findAll();
    }

    /*
     * Questo metodo permette di recuperare un evcento dal suo id
     * 
     * @return l'oggetto Evento
     * 
     * @param l'id dell'evento
     */
    @Override
    public Evento recuperaUno(long id) {

        Optional<Evento> e = eventoRepo.findById(id);

        return e.isEmpty() ? null : e.get();
    }

    /*
     * Questo metodo permette di salvare un Evento nel Database
     * 
     * @return valore booleano che identifica il successo o fallimento
     * dell'operazione
     * 
     * @param l'oggetto Evento da salvare
     */
    @Override
    public boolean salva(Evento evento) {

        boolean esito = true;

        try {
            eventoRepo.save(evento);
        } catch (Exception e) {
            esito = false;
        }

        return esito;
    }

    /*
     * Questo metodo permette di rimuovere un Evento dal Database
     * 
     * @return void
     * 
     * @param id dell'evento da eliminare
     */
    @Override
    public void elimina(Long id) {

        eventoRepo.deleteById(id);
    }

    /*
     * Questo metodo permette di recuperare la lista degli Eventi presenti nel DB
     * che appartengono ad una determinata e specifica categoria
     * 
     * @return lista di eventi
     * 
     * @param la categoria da filtrare
     */
    public List<Evento> recuperaEventiByCategoria(String categoria) {

        return eventoRepo.findByCategoria(categoria);
    }

    /*
     * Questo metodo permette di recuperare la lista degli Eventi presenti nel DB
     * che risalgono ad una specifica data
     * 
     * @return lista di eventi
     * 
     * @param la data da filtrare
     */
    public List<Evento> recuperaEventiByData(LocalDate data) {

        return eventoRepo.findByData(data);
    }

}
