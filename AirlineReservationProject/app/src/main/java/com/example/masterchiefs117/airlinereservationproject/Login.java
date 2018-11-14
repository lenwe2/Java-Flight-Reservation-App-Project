package com.example.masterchiefs117.airlinereservationproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.masterchiefs117.airlinereservationproject.Account.accounts;
import static com.example.masterchiefs117.airlinereservationproject.MainMenu.reservationNumber;
import static com.example.masterchiefs117.airlinereservationproject.ReserveSeat.availableFlights;
import static com.example.masterchiefs117.airlinereservationproject.ReserveSeat.selectedIndex;
import static com.example.masterchiefs117.airlinereservationproject.ReserveSeat.ticketAmount;
import static com.example.masterchiefs117.airlinereservationproject.Transaction.transactions;

/*
Title : Login.java
Abtract: Activity the makes you login and finishes reserving the flights
Author: Nicholas Andrada
Date: 5/13/18
*/
public class Login extends AppCompatActivity {

    // Login for the reserve seat

    private EditText editTextUsername, editTextPassword;
    private Button btnLogin;
    private String accountKey;
    private String user;
    private int errorCounter;
    private ArrayList<Flight> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassWord);


        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verification(user = editTextUsername.getText().toString(), editTextPassword.getText().toString())) {
                    // Display flight information
                    temp = availableFlights;
                    reservationNumber += 1;
                    temp.get(selectedIndex).setReservationCounter(reservationNumber);
                    NumberFormat formatter = new DecimalFormat("#0.00");
                    Double price = temp.get(selectedIndex).getPrice() * ticketAmount;
                    String info = "Username: " + user + "\n" + temp.get(selectedIndex).toString() + "Number of Tickets: " + temp.get(selectedIndex).getTicketAmount() + "\n" + "Reservation Number: "
                            + temp.get(selectedIndex).getReservationCounter() + "\n" + "Amount owed: $" + formatter.format(price);
                    AlertDialog.Builder infoMessage = new AlertDialog.Builder(Login.this);
                    infoMessage.setMessage(info).setCancelable(false)
                            .setPositiveButton("Confirm Flight", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    accounts.get(accountKey).getReservedFlights().add(availableFlights.get(selectedIndex));
                                    Calendar calendar = Calendar.getInstance();
                                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                                    String date = DateFormat.getDateInstance().format(calendar.getTime());
                                    String time = format.format(calendar.getTime());
                                    transactions.add(new Transaction("Reserve Seat", user, temp.get(selectedIndex).getFlightNumber(), temp.get(selectedIndex).getDeparture(),
                                            temp.get(selectedIndex).getArrival(), temp.get(selectedIndex).getTicketAmount(), temp.get(selectedIndex).getReservationCounter(), temp.get(selectedIndex).getPrice() * ticketAmount,
                                            date, time, temp.get(selectedIndex).getTime()));
                                 Intent createIntent = new Intent(Login.this, MainMenu.class);
                                 startActivity(createIntent);
                                }
                            });
                    AlertDialog infoDialog = infoMessage.create();
                    infoDialog.setTitle("Flight Information");
                    infoDialog.show();
                }
                else if (errorCounter < 1 ) {
                    AlertDialog.Builder errorMessage = new AlertDialog.Builder(Login.this);
                    errorMessage.setMessage("Please re-enter the username and password").setCancelable(false)
                            .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    errorCounter++;
                                }
                            });
                    AlertDialog error = errorMessage.create();
                    error.setTitle("Error");
                    error.show();
                }
                else {
                    AlertDialog.Builder errorMessage = new AlertDialog.Builder(Login.this);
                    errorMessage.setMessage("Incorrect username and/or password").setCancelable(false)
                            .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent createIntent = new Intent(Login.this, MainMenu.class);
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

    public boolean verification(String user, String pass) {
        for (String i: accounts.keySet()) {
            if (user.equals(accounts.get(i).getUsername()) && pass.equals(accounts.get(i).getPassword())) {
                accountKey = i;
                return true;
            }
        }
        return false;
    }
}
