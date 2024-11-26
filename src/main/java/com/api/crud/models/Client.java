package com.api.crud.models;

import jakarta.persistence.Entity;

@Entity
public class Client extends Person {

    private String password;
    private Boolean status;

    // Default constructor
    public Client() {}


    public Client(String name, String gender, Number idNumber, String address, String phoneNumber, String password, Boolean status) {
        super(name, gender, idNumber, address, phoneNumber);  // Call to the Person constructor
        this.password = password;
        this.status = status;
    }

    // Getters and setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
