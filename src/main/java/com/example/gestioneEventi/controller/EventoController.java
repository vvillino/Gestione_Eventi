package com.example.gestioneEventi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.gestioneEventi.services.EventoServiceImpl;

@Controller
public class EventoController {

    @Autowired
    private EventoServiceImpl eventoService;

    @GetMapping("/")
    public String homepage() {

        return "evento";
    }

    @GetMapping("/eventi")
    public String getAllEvents(Model model) {

        model.addAttribute("eventi", eventoService.recuperaTutti());

        return "listaEventi";
    }

    @GetMapping("/{categoria}")
    public String getEventiCategoria(@PathVariable String categoria, Model model) {

        model.addAttribute("eventi", eventoService.recuperaEventiByCategoria(categoria));

        return "listaEventi";
    }

}
