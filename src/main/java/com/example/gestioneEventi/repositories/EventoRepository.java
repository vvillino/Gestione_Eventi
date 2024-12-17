package com.example.gestioneEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestioneEventi.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

}
