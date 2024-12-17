package com.example.gestioneEventi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.gestioneEventi.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Utente U WHERE U.email = :email AND U.password = :password")
    public Optional<Utente> findByEmailePassword(@Param("email") String email, @Param("password") String password);

    @Query(nativeQuery = true, value = "SELECT U.nome, U.cognome FROM Utenti u JOIN Partecipanti p ON u.id_utente = p.id_Utente WHERE P.id_evento = :id_evento")
    public List<Utente> findAllByEvento(@Param("id_evento") Long id);

}
