package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany()
    private List<SalesInformation> salesInformation;
    @ManyToMany()
    private List<Game> games;

    @OneToOne()
    private User user;

    public Customer() {
    }

    public Customer(Long id, List<SalesInformation> salesInformation, List<Game> games, User user) {
        this.id = id;
        this.salesInformation = salesInformation;
        this.games = games;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SalesInformation> getSalesInformation() {
        return salesInformation;
    }

    public void setSalesInformation(List<SalesInformation> salesInformation) {
        this.salesInformation = salesInformation;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
