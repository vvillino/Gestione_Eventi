package com.example.gestioneEventi.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestioneEventi.model.Evento;
import com.example.gestioneEventi.services.EventoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // http://localhost:8080/gestione_eventi/eventi
    @GetMapping
    public List<Evento> getAllEvents() {

        return eventoService.recuperaTutti();
    }

    // http://localhost:8080/gestione_eventi/eventi/1
    @GetMapping("/{id}")
    public ResponseEntity<Evento> getEventiById(@PathVariable Long id) {

        Evento evento = eventoService.recuperaUno(id);

        if (evento != null)
            return ResponseEntity.ok(evento);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // http://localhost:8080/gestione_eventi/eventi/categoria/sport
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<Evento>> getEventiByCategoria(@PathVariable String categoria) {

        List<Evento> eventi = eventoService.recuperaEventiByCategoria(categoria);

        if (eventi != null)
            return ResponseEntity.status(HttpStatus.OK).body(eventi);
        else
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    // http://localhost:8080/gestione_eventi/eventi/data/2024-03-10
    @GetMapping("/data/{data}")
    public ResponseEntity<List<Evento>> getEventiByData(@PathVariable LocalDate data) {

        List<Evento> eventi = eventoService.recuperaEventiByData(data);

        if (eventi != null)
            return ResponseEntity.status(HttpStatus.OK).body(eventi);
        else
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).build();
    }

    // http://localhost:8080/gestione_eventi/eventi/crea
    @PostMapping("/crea")
    public ResponseEntity<Evento> creaEvento(@RequestBody @Valid Evento evento) {

        if (eventoService.salva(evento))
            return ResponseEntity.status(HttpStatus.CREATED).body(evento);
        else
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    // http://localhost:8080/gestione_eventi/eventi/8
    @PutMapping("/{id}")
    public ResponseEntity<Evento> modificaEvento(@PathVariable Long id, @RequestBody @Valid Evento evento) {

        Evento eventoTrovato = eventoService.recuperaUno(id);

        if (eventoTrovato != null) {

            evento.setId(eventoTrovato.getId());
            eventoService.salva(evento);
            return ResponseEntity.status(HttpStatus.OK).body(evento);
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // http://localhost:8080/gestione_eventi/eventi/8
    @DeleteMapping("/{id}")
    public ResponseEntity<Evento> elimina(@PathVariable Long id) {

        Evento eventoTrovato = eventoService.recuperaUno(id);

        if (eventoTrovato != null) {

            eventoService.elimina(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

}
