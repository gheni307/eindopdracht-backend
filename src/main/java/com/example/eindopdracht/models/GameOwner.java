package com.example.eindopdracht.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "gameOwner")
public class GameOwner {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private User user;

    @OneToOne()
    @JoinColumn(name = "imageData")
    private ImageData imageData;

    @OneToMany(mappedBy = "gameOwner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<Game> game;

    @OneToOne()
    private SalesInformation salesInformation;

    public GameOwner() {
    }

    public GameOwner(Long id, User user, ImageData imageData, Collection<Game> game, SalesInformation salesInformation) {
        this.id = id;
        this.user = user;
        this.imageData = imageData;
        this.game = game;
        this.salesInformation = salesInformation;
    }

    public ImageData getImageData() {
        return imageData;
    }

    public void setImageData(ImageData imageData) {
        this.imageData = imageData;
    }

    public SalesInformation getSalesInformation() {
        return salesInformation;
    }

    public void setSalesInformation(SalesInformation salesInformation) {
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

    public Collection<Game> getGame() {
        return game;
    }

    public void setGame(Collection<Game> game) {
        this.game = game;
    }
}

