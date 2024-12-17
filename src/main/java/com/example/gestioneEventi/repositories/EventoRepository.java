package com.example.gestioneEventi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestioneEventi.model.Evento;
import com.example.gestioneEventi.model.Utente;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
