package com.example.gestioneEventi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gestioneEventi.services.EventoService;

@Controller
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/")
    public String homepage() {

        return "evento";
    }

    @GetMapping("/eventi")
    public String getAllEvents(Model model) {

        model.addAttribute("eventi", eventoService.recuperaTutti());

        return "listaEventi";
    }

}
