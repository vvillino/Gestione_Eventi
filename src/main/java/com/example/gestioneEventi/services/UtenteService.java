package com.example.gestioneEventi.services;

import com.example.gestioneEventi.model.Utente;

public interface UtenteService {

    public Utente recuperaUno(long id);

    public Boolean salva(Utente utente);

    public void elimina(Long id);

}
