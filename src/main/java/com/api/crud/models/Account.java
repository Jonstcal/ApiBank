package com.api.crud.models;

import jakarta.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountNumber;
    private String accountType;
    private Double initialBalance;
    private Double balance;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Account() {
    }

    public Account(Long accountNumber, String accountType, Double initialBalance, boolean status, Customer customer) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.initialBalance = initialBalance;
        this.status = status;
        this.balance = initialBalance;
        this.customer = customer;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Double getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(Double initialBalance) {
        this.initialBalance = initialBalance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    //Debit and credit methods
    public boolean debit(Double amount) {
        if (amount > 0 && this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    public void credit(Double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    public boolean hasSufficientBalance(Double amount) {
        return this.balance >= amount;
    }

    @Override
    public String toString() {
        return "Account{id=" + id + ", accountNumber=" + accountNumber + ", accountType='" + accountType + '\'' +
                ", initialBalance=" + initialBalance + ", balance=" + balance + ", status=" + status + ", customer=" + customer.getId() + '}';
    }
}
