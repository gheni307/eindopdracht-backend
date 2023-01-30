package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
public class SalesInformation {

    @Id
    @GeneratedValue
    private Long id;
    private Boolean hasBeenSold;
    private Boolean subscribed;

    @OneToOne(mappedBy = "salesInformation")
    private GameOwner gameOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    public SalesInformation() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
