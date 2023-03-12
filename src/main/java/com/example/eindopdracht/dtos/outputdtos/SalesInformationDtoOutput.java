package com.example.eindopdracht.dtos.outputdtos;

import com.example.eindopdracht.models.Customer;
import com.example.eindopdracht.models.GameOwner;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SalesInformationDtoOutput {

    private Long id;
    private Boolean hasBeenSold;
    private Boolean subscribed;
    @JsonIncludeProperties("id")
    private GameOwner gameOwner;
    @JsonIncludeProperties("id")
    private List<Customer> customers;

    public SalesInformationDtoOutput() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getHasBeenSold() {
        return hasBeenSold;
    }

    public void setHasBeenSold(Boolean hasBeenSold) {
        this.hasBeenSold = hasBeenSold;
    }

    public Boolean getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Boolean subscribed) {
        this.subscribed = subscribed;
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
