package com.example.gestioneEventi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestioneEventi.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    // SELECT * FROM Utente U WHERE U.email = email AND U.password = password
    public Optional<Utente> findByEmailPassword(String email, String password);

}
