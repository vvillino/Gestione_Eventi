package com.example.gestioneEventi.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;
    @Column(name = "descrizione")
    private String descrizione;
    @Column(name = "categoria")
    private String categoria;

    @Column(name = "data")
    // @JsonFormat permette di visualizzare la data nel formato specificato dal
    // pattern
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate data;

    @ManyToMany
    @JoinTable(name = "partecipanti", joinColumns = @JoinColumn(name = "id_evento", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_utente", referencedColumnName = "id", nullable = true))
    private List<Utente> utenti;

    public Evento() {

    }

    public Evento(Long id, String nome, String descrizione, String categoria, LocalDate data) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.categoria = categoria;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Evento [id=" + id + ", nome=" + nome + ", descrizione=" + descrizione + ", categoria=" + categoria
                + ", data=" + data + "]";
    }

    // @JsonIgnore permette di escludere l'attributo da problemi di serializzazione
    // ciclica
    @JsonIgnore
    public List<Utente> getUtenti() {
        return utenti;
    }

}
