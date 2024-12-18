package com.example.gestioneEventi.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @Override
    public List<Evento> recuperaTutti() {

        return eventoRepo.findAll();
    }

    @Override
    public Evento recuperaUno(long id) {

        Optional<Evento> e = eventoRepo.findById(id);

        return e.isEmpty() ? null : e.get();
    }

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

    @Override
    public void elimina(Long id) {

        eventoRepo.deleteById(id);
    }

    public List<Evento> recuperaEventiByCategoria(String categoria) {

        return eventoRepo.findByCategoria(categoria);
    }

    public List<Evento> recuperaEventiByData(LocalDate data) {

        return eventoRepo.findByData(data);
    }

}
