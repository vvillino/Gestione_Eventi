package com.example.gestioneEventi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.gestioneEventi.model.Evento;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

    public List<Evento> findByData(LocalDate data);

    public List<Evento> findByCategoria(String categoria);

}
