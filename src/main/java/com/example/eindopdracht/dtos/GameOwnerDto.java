package com.example.eindopdracht.dtos;

import com.example.eindopdracht.models.Authority;
import com.example.eindopdracht.models.SalesInformation;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Set;

public class GameOwnerDto {

    public String username;
    public String password;
    public Boolean enabled;
    public String apikey;
    public String email;

    public byte[] imageData;
    public SalesInformation salesInformation;
    @JsonSerialize
    public Set<Authority> authorities;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getApikey() {
        return apikey;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SalesInformation getSalesInformation() {
        return salesInformation;
    }

    public void setSalesInformation(SalesInformation salesInformation) {
        this.salesInformation = salesInformation;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
