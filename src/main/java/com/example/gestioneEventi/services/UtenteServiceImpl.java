package com.example.gestioneEventi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestioneEventi.model.Utente;
import com.example.gestioneEventi.repositories.UtenteRepository;

@Service
public class UtenteServiceImpl implements UtenteService {

    @Autowired
    private UtenteRepository utenteRepo;

    @Override
    public Utente recuperaUno(long id) {

        Optional<Utente> u = utenteRepo.findById(id);
        return u.isEmpty() ? null : u.get();
    }

    public Utente recuperaByEmailePassword(String email, String password) {

        Optional<Utente> u = utenteRepo.findByEmailPassword(email, password);

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

    @Override
    public void elimina(Long id) {
        utenteRepo.deleteById(id);
    }

}
