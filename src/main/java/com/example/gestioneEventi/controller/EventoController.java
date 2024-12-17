package com.example.gestioneEventi.gestioneEventi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventoController {

    @GetMapping("/")
    public String homepage() {

        return "evento";
    }

}
