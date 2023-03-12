package com.example.eindopdracht.dtos;

import com.example.eindopdracht.models.SalesInformation;
import com.example.eindopdracht.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

public class GameOwnerDto {

    private Long id;

    @JsonIncludeProperties("username")
    private User user;

    @JsonIncludeProperties("id")
    private SalesInformation salesInformation;

    public GameOwnerDto() {
    }

    public GameOwnerDto(Long id, User user, SalesInformation salesInformation) {
        this.id = id;
        this.user = user;
        this.salesInformation = salesInformation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public SalesInformation getSalesInformation() {
        return salesInformation;
    }

    public void setSalesInformation(SalesInformation salesInformation) {
        this.salesInformation = salesInformation;
    }

}
