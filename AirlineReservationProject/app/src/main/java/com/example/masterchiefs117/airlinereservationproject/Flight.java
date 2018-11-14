package com.example.masterchiefs117.airlinereservationproject;

import java.util.ArrayList;

/*
Title : Flight.java
Abtract: Holds all of the information for the flights
Author: Nicholas Andrada
Date: 5/13/18
*/

public class Flight {
    private int flightCapacity;
    private int seatsFilled;
    private String departure;
    private String arrival;
    private String flightNumber;
    private String time;
    private double price;
    private int ticketAmount;
    private int reservationCounter = 0;

    public static ArrayList<Flight> flights = new ArrayList<>();

    public Flight(String flightNumber, String departure, String arrival, String time, int flightCapacity, int seatsFilled, double price) {
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.time = time;
        this.flightCapacity = flightCapacity;
        this.seatsFilled = seatsFilled;
        this.price = price;
        ticketAmount = 0;
    }

    @Override
    public String toString() {
        return "Flight Number: " + flightNumber + "\n" + "Departure: " + departure + "\n" + "Arrival: " + arrival
                + "\n" + "Departure Time: " + time + "\n";

    }

    // Getters and Setters

    public int getReservationCounter() {
        return reservationCounter;
    }

    public void setReservationCounter(int reservationCounter) {
        this.reservationCounter = reservationCounter;
    }

    public int getTicketAmount() {
        return ticketAmount;
    }

    public void setTicketAmount(int ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSeatsFilled() {
        return seatsFilled;
    }

    public void setSeatsFilled(int seatsFilled) {
        this.seatsFilled = seatsFilled;
    }

    public int getFlightCapacity() {
        return flightCapacity;
    }

    public void setFlightCapacity(int flightCapacity) {
        this.flightCapacity = flightCapacity;
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

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
