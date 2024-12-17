package com.example.gestioneEventi.services;

import java.util.List;

import com.example.gestioneEventi.model.Utente;

public interface UtenteService {

    public Utente recuperaUno(long id);

    public List<Utente> recuperaByEvento(Long id);

    public Boolean salva(Utente utente);

    public void elimina(Long id);

}
