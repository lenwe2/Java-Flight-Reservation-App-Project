package com.example.masterchiefs117.airlinereservationproject;

import java.util.ArrayList;
import java.util.HashMap;


/*
Title : Account.java
Abtract: Holds all of the account information
Author: Nicholas Andrada
Date: 5/13/18
*/
public class Account {
    private String username;
    private String password;
    public static HashMap<String, Account> accounts = new HashMap<>();
    private ArrayList<Flight> reservedFlights = new ArrayList<>();

    public Account (String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public ArrayList<Flight> getReservedFlights() {
        return reservedFlights;
    }

    public void setReservedFlights(ArrayList<Flight> reservedFlights) {
        this.reservedFlights = reservedFlights;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}