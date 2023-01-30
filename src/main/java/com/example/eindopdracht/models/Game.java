package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String compatible;
    private String genre;
    private Double price;

    @ManyToOne
    private GameOwner gameOwner;

    public Game() {
    }

    public Game(Long id, String name, String compatible, String genre, Double price) {
        this.id = id;
        this.name = name;
        this.compatible = compatible;
        this.genre = genre;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompatible() {
        return compatible;
    }

    public void setCompatible(String compatible) {
        this.compatible = compatible;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public GameOwner getGameOwner() {
        return gameOwner;
    }

    public void setGameOwner(GameOwner gameOwner) {
        this.gameOwner = gameOwner;
    }
}
