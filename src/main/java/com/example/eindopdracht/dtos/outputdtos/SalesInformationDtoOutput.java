package com.example.eindopdracht.dtos.outputdtos;

import com.example.eindopdracht.models.GameOwner;
import com.example.eindopdracht.models.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SalesInformationDtoOutput {

    private Long id;
    private Boolean hasBeenSold;
    private Boolean subscribed;
    private GameOwner gameOwner;
    private List<User> userList;

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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
