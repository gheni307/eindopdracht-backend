package com.example.eindopdracht.dtos.inputdtos;

import com.example.eindopdracht.models.Customer;
import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SalesInformationDtoInput {

    @NotNull
    private Boolean hasBeenSold;
    @NotNull
    private Boolean subscribed;
    private GameOwner gameOwner;
    @JsonIgnore
    private List<Customer> customers;

    public SalesInformationDtoInput() {
    }

    public SalesInformationDtoInput(Boolean hasBeenSold, Boolean subscribed) {
        this.hasBeenSold = hasBeenSold;
        this.subscribed = subscribed;
    }

    public SalesInformationDtoInput(Boolean hasBeenSold, Boolean subscribed, GameOwner gameOwner, List<Customer> customers) {
        this.hasBeenSold = hasBeenSold;
        this.subscribed = subscribed;
        this.gameOwner = gameOwner;
        this.customers = customers;
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
