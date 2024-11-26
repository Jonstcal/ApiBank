package com.api.crud.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Date of the movement
    private String type; // Movement type: "debit" or "credit"
    private Double value; // Amount of the transaction
    private Double balance; // Balance after the transaction

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account; // Relation to the account

    // Default constructor
    public Movement() {}

    // Constructor with parameters
    public Movement(LocalDate date, String type, Double value, Double balance, Account account) {
        this.date = date;
        this.type = type;
        this.value = value;
        this.balance = balance;
        this.account = account;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Account getAccount() {
        return account; // Getter for the account
    }

    public void setAccount(Account account) {
        this.account = account; // Setter for the account
    }

    @Override
    public String toString() {
        return "Movement{" +
                "id=" + id +
                ", date=" + date +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", balance=" + balance +
                ", account=" + account +
                '}';
    }
}
