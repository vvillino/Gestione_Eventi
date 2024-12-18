package com.example.gestioneEventi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.gestioneEventi.model.Utente;
import com.example.gestioneEventi.services.UtenteServiceImpl;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/utenti")
public class UtenteController {
    
    @Autowired
    private UtenteServiceImpl service;

    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUser(@PathVariable Long id) {

        if (service.recuperaUno(id) != null) {
            return ResponseEntity.status(HttpStatus.OK).body(service.recuperaUno(id));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/.....")
    public ResponseEntity<List<Utente>> getMethodName(@RequestParam String param) {
        // TODO ....
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Utente> deleteUser(@PathVariable Long id) {
        
        Utente u = service.recuperaUno(id);

        if (u != null) {
            service.elimina(id);
            return ResponseEntity.status(HttpStatus.OK).body(u);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
