package com.example.eindopdracht.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Subscription {

    @Id
    @GeneratedValue
    private Long id;

    private Date startDate;
    private Date eindDate;
    private Boolean switches;
    private Double price;
    private Double rechargeAmount;

    public Subscription() {
    }

    public Subscription(Long id, Date startDate, Date eindDate, Boolean switches, Double price, Double rechargeAmount) {
        this.id = id;
        this.startDate = startDate;
        this.eindDate = eindDate;
        this.switches = switches;
        this.price = price;
        this.rechargeAmount = rechargeAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEindDate() {
        return eindDate;
    }

    public void setEindDate(Date eindDate) {
        this.eindDate = eindDate;
    }

    public Boolean getSwitches() {
        return switches;
    }

    public void setSwitches(Boolean switches) {
        this.switches = switches;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(Double rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }
}
