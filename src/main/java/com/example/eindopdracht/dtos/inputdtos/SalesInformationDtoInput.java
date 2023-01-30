package com.example.eindopdracht.dtos.inputdtos;

import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.models.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public class SalesInformationDtoInput {

    @NotNull
    private Boolean hasBeenSold;
    @NotNull
    private Boolean subscribed;
    private GameOwner gameOwner;
    private List<User> users;

    public SalesInformationDtoInput() {
    }

    public SalesInformationDtoInput(Boolean hasBeenSold, Boolean subscribed) {
        this.hasBeenSold = hasBeenSold;
        this.subscribed = subscribed;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
