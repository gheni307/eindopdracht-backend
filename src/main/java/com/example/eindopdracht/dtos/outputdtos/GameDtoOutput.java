package com.example.eindopdracht.dtos.outputdtos;

import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.models.ImageData;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

public class GameDtoOutput {

    Long id;

    private String name;
    private String compatible;
    private String genre;
    private Double price;

    private GameOwner gameOwner;

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
