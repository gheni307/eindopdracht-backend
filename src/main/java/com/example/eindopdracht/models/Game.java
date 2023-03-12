package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String name;
    private String compatible;
    private String genre;
    private Double price;

    @ManyToOne()
    private GameOwner gameOwner;


    @ManyToMany(mappedBy = "games",cascade = CascadeType.ALL)
    private List<Customer> customers;

    public Game() {
    }

    public Game(Long id, String name, String compatible, String genre, Double price) {
        this.id = id;
        this.name = name;
        this.compatible = compatible;
        this.genre = genre;
        this.price = price;
    }

    public Game(Long id, String name, String compatible, String genre, Double price, GameOwner gameOwner, List<Customer> customers) {
        this.id = id;
        this.name = name;
        this.compatible = compatible;
        this.genre = genre;
        this.price = price;
        this.gameOwner = gameOwner;
        this.customers = customers;
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

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
