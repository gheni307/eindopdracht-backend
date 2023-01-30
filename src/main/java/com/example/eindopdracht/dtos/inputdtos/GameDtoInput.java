package com.example.eindopdracht.dtos.inputdtos;

import com.example.eindopdracht.models.GameOwner;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.validation.constraints.NotNull;

public class GameDtoInput {


    @NotNull
    private String name;
    @NotNull
    private String compatible;
    @NotNull
    private String genre;
    @NotNull
    private Double price;

    public GameDtoInput(String name, String compatible, String genre, Double price) {
        this.name = name;
        this.compatible = compatible;
        this.genre = genre;
        this.price = price;
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

}
