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

import static com.example.masterchiefs117.airlinereservationproject.Flight.flights;

/*
Title : AddNewFlight.java
Abtract: Activity that holds the information for adding a new flight
Author: Nicholas Andrada
Date: 5/13/18
*/

public class AddNewFlight extends AppCompatActivity {

    private EditText flightNumber, editTextTime, editTextFlightCapacity, editTextPrice;
    private Button btnConfirmation;
    private Spinner arrive, depart;
    private int flightCap;
    private double price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_flight);

        flightNumber = findViewById(R.id.editTextFlightNumber);
        editTextTime = findViewById(R.id.editTextTime);
        editTextFlightCapacity = findViewById(R.id.editTextFlightCapacity);
        editTextPrice = findViewById(R.id.editTextPrice);

        btnConfirmation = findViewById(R.id.btnConfirmation);

        arrive = findViewById(R.id.spinnerArrive);
        depart = findViewById(R.id.spinnerDepart);

        ArrayAdapter<String> CitiesAdapter = new ArrayAdapter<>(AddNewFlight.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.cities));
        CitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        depart.setAdapter(CitiesAdapter);
        arrive.setAdapter(CitiesAdapter);

        btnConfirmation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (verifyFlightInfo()) {
                    flightCap = Integer.parseInt(editTextFlightCapacity.getText().toString());
                    price = Double.parseDouble(editTextPrice.getText().toString());
                    flights.add(new Flight(flightNumber.getText().toString(), depart.getSelectedItem().toString(), arrive.getSelectedItem().toString(), editTextTime.getText().toString(), flightCap,
                            0, price));

                    AlertDialog.Builder confirmMessage = new AlertDialog.Builder(AddNewFlight.this);
                    confirmMessage.setTitle("Info Confirmation");
                    confirmMessage.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent createIntent = new Intent(AddNewFlight.this, MainMenu.class);
                            startActivity(createIntent);
                        }
                    });
                    confirmMessage.show();
                }
                else {
                    AlertDialog.Builder error = new AlertDialog.Builder(AddNewFlight.this);
                    error.setTitle("Error");
                    error.setMessage("Flight Number already exists");
                    error.setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent createIntent = new Intent(AddNewFlight.this, MainMenu.class);
                            startActivity(createIntent);
                        }
                    });
                    error.show();
                }
            }
        });
    }

    public boolean verifyFlightInfo() {
        for (Flight f: flights) {
            if (f.getFlightNumber().equals(flightNumber.getText().toString())) {
                return false;
            }
        }
        return true;
    }
}
