package com.example.gestioneEventi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestioneEventi.model.Evento;
import com.example.gestioneEventi.services.EventoService;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public List<Evento> getAllEvents() {

        return eventoService.recuperaTutti();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventiById(@PathVariable Long id) {

        Evento evento = eventoService.recuperaUno(id);

        if (evento != null)
            return ResponseEntity.ok(evento);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{categoria}")
    public ResponseEntity<Evento> getEventiByCategoria(@PathVariable String categoria) {

        Evento evento = eventoService.recuperaEventiByCategoria(categoria);

        if (evento != null)
            return ResponseEntity.ok(evento);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
