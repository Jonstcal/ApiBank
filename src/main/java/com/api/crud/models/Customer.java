package com.api.crud.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;


@Entity
@DiscriminatorValue("customer")
public class Customer extends Person {

    private String password;
    private boolean active;

    // Getters y Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public boolean getActive() {
        return this.active;
    }


    public void setActive(boolean active) {
        this.active = active;
    }

}
