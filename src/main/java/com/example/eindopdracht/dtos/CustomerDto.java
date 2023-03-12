package com.example.eindopdracht.dtos;

import com.example.eindopdracht.models.Game;
import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;

public class CustomerDto {

    public Long id;

    @JsonIgnore
    private List<SalesInformation> salesInformation;

    @JsonIgnore
    private List<Game> games;

    @JsonIncludeProperties("username")
    private User user;

    public CustomerDto() {
    }

    public CustomerDto(Long id, List<SalesInformation> salesInformation, List<Game> games, User user) {
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
