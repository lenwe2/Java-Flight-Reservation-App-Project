package com.example.masterchiefs117.airlinereservationproject;

import java.util.ArrayList;

/*
Title : Transaction.java
Abtract: Class that stores the transaction information
Author: Nicholas Andrada
Date: 5/13/18
*/
public class Transaction {
    private String transactionType;
    private String time;
    private String username;
    private String flightNumber;
    private String departure;
    private String arrival;
    private String currentDate;
    private String currentTime;
    private int tickets;
    private int reservationNumber;
    private double price;

    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public Transaction(String transactionType, String username, String flightNumber, String departure, String arrival, int tickets, int reservationNumber, double price,
                       String currentDate, String currentTime, String time) {
        this.transactionType = transactionType;
        this.username = username;
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.tickets = tickets;
        this.reservationNumber = reservationNumber;
        this.price = price;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
        this.time = time;
    }

    // Getters and Setters

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public int getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(int reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
