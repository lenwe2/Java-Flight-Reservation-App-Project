package com.example.masterchiefs117.airlinereservationproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import static com.example.masterchiefs117.airlinereservationproject.Flight.flights;

/*
Title : ReserveSeat.java
Abtract: Activity that lets you reserve a seat
Author: Nicholas Andrada
Date: 5/13/18
*/
public class ReserveSeat extends AppCompatActivity {

    private Spinner Departure, Arrival;
    private EditText Ticket;
    private Button btnConfirm;
    public static ArrayList<Flight> availableFlights = new ArrayList<>();
    public static int ticketAmount;
    public static int selectedIndex;
    public String departure, arrival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        Departure = findViewById(R.id.spinnerDeparture);
        Arrival = findViewById(R.id.spinnerArrival);
        Ticket = findViewById(R.id.editTextTicket);

        ArrayAdapter<String> CitiesAdapter = new ArrayAdapter<>(ReserveSeat.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cities));
        CitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Departure.setAdapter(CitiesAdapter);
        Arrival.setAdapter(CitiesAdapter);

        btnConfirm = findViewById(R.id.btnConfirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flightCheck(departure = Departure.getSelectedItem().toString(), arrival = Arrival.getSelectedItem().toString(), ticketAmount = Integer.parseInt(Ticket.getText().toString()))) {
                    if (ticketAmount > 7) {
                        AlertDialog.Builder errorMessage = new AlertDialog.Builder(ReserveSeat.this);
                        errorMessage.setMessage("Reservation can't be made due to system restriction");
                        errorMessage.setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog error = errorMessage.create();
                        error.setTitle("Error");
                        error.show();
                    }
                    else {
                        availableFlights.clear();
                        availableFlights.addAll(getFlights(departure, arrival, ticketAmount));
                        String[] list = new String[availableFlights.size()];
                        for (int i = 0; i < availableFlights.size(); i++) {
                            list[i] = availableFlights.get(i).getFlightNumber();
                            availableFlights.get(i).setTicketAmount(ticketAmount);
                        }

                        AlertDialog.Builder flightDisplay = new AlertDialog.Builder(ReserveSeat.this);
                        flightDisplay.setTitle("Available Flights");
                        flightDisplay.setSingleChoiceItems(list, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                selectedIndex = which;
                            }
                        });
                        flightDisplay.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent createIntent = new Intent(ReserveSeat.this, Login.class);
                                startActivity(createIntent);
                            }
                        });
                        flightDisplay.show();
                    }
                }
                else {
                    AlertDialog.Builder errorMessage = new AlertDialog.Builder(ReserveSeat.this);
                    errorMessage.setMessage("No flight available for the information entered.");
                    errorMessage.setNeutralButton("Exit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent createIntent = new Intent(ReserveSeat.this, MainMenu.class);
                            startActivity(createIntent);
                        }
                    });
                    AlertDialog error = errorMessage.create();
                    error.setTitle("Error");
                    error.show();
                }
            }
        });
    }

    public boolean flightCheck(String departure, String arrival, int ticketAmount) {
        for (Flight temp : flights) {
            if((temp.getDeparture().equals(departure)) && (temp.getArrival().equals(arrival)) && (temp.getFlightCapacity() >= ticketAmount)
                    && ((temp.getSeatsFilled() + ticketAmount) <= temp.getFlightCapacity())) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Flight> getFlights (String departure, String arrival, int ticketAmount) {
        ArrayList<Flight> flightList = new ArrayList<>();
        for (Flight temp : flights) {
            if(temp.getDeparture().equals(departure) && temp.getArrival().equals(arrival) && temp.getFlightCapacity() >= ticketAmount
                    && (temp.getSeatsFilled() + ticketAmount) <= temp.getFlightCapacity()) {
                flightList.add(temp);
            }
        }
        return flightList;
    }
}
