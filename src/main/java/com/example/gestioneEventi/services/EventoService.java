package com.example.gestioneEventi.services;

import java.util.List;

import com.example.gestioneEventi.model.Evento;

public interface EventoService {

    public List<Evento> recuperaTutti();

    public Evento recuperaUno(long id);

    public boolean salva(Evento evento);

    public void elimina(Long id);

}
