package com.example.eindopdracht.dtos.outputdtos;

import com.example.eindopdracht.models.Customer;
import com.example.eindopdracht.models.GameOwner;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.util.List;
import java.util.Objects;


public class GameDtoOutput {

    Long id;

    private String name;
    private String compatible;
    private String genre;
    private Double price;

    @JsonIncludeProperties("id")
    private GameOwner gameOwner;

    @JsonIncludeProperties("id")
    private List<Customer> customerList;

    public GameDtoOutput() {
    }

    public GameDtoOutput(Long id, String name, String compatible, String genre, Double price) {
        this.id = id;
        this.name = name;
        this.compatible = compatible;
        this.genre = genre;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameDtoOutput dtoOutput = (GameDtoOutput) o;
        return Objects.equals(id, dtoOutput.id) && Objects.equals(name, dtoOutput.name) && Objects.equals(compatible, dtoOutput.compatible) && Objects.equals(genre, dtoOutput.genre) && Objects.equals(price, dtoOutput.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, compatible, genre, price);
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

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
