package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
public class SalesInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean hasBeenSold;
    private Boolean subscribed;

    @OneToOne(mappedBy = "salesInformation", cascade = CascadeType.ALL)
    private GameOwner gameOwner;

    @ManyToMany(mappedBy = "salesInformation", cascade = CascadeType.ALL)
    private List<Customer> customers;

    public SalesInformation() {
    }

    public SalesInformation(Long id, Boolean hasBeenSold, Boolean subscribed) {
        this.id = id;
        this.hasBeenSold = hasBeenSold;
        this.subscribed = subscribed;
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