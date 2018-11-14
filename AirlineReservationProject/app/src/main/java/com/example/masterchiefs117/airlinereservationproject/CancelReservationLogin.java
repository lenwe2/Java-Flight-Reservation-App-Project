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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.masterchiefs117.airlinereservationproject.Account.accounts;
import static com.example.masterchiefs117.airlinereservationproject.Transaction.transactions;

/*
Title : CancelReservationLogin.java
Abtract: Activity that lets you cancel a reservation
Author: Nicholas Andrada
Date: 5/13/18
*/
public class CancelReservationLogin extends AppCompatActivity {

    private EditText editTxtUsername, editTxtPassword;
    private Button btnLogIn;
    private String accountKey;
    private String user;
    private int errorCounter;
    private int index;
    private ArrayList<Flight> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        editTxtUsername = findViewById(R.id.editTxtUsername);
        editTxtPassword = findViewById(R.id.editTxtPassword);

        btnLogIn = findViewById(R.id.btnLogIn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(verification(user = editTxtUsername.getText().toString(), editTxtPassword.getText().toString())) {
                    temp = accounts.get(user).getReservedFlights();

                    if (temp.isEmpty()) {
                        AlertDialog.Builder failedMessage = new AlertDialog.Builder(CancelReservationLogin.this);
                        failedMessage.setMessage("No reservations to cancel").setCancelable(false)
                                .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent createIntent = new Intent(CancelReservationLogin.this, MainMenu.class);
                                        startActivity(createIntent);
                                    }
                                });
                        failedMessage.show();
                    }
                    else {
                        String[] flightList = new String[temp.size()];
                        for (int i = 0; i < temp.size(); i++) {
                            flightList[i] = "Reservation Number: " + temp.get(i).getReservationCounter() + "\n" + temp.get(i).toString() + "Ticket Amount: " + temp.get(i).getTicketAmount();
                        }

                        AlertDialog.Builder flightDisplay = new AlertDialog.Builder(CancelReservationLogin.this);
                        flightDisplay.setTitle("Reserved Flights");
                        flightDisplay.setSingleChoiceItems(flightList, -1, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                index = which;
                            }
                        });
                        flightDisplay.setPositiveButton("Confirm Cancelation", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Calendar calendar = Calendar.getInstance();
                                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                                String date = DateFormat.getDateInstance().format(calendar.getTime());
                                String time = format.format(calendar.getTime());
                                transactions.add(new Transaction("Cancel Reservation", user, temp.get(index).getFlightNumber() , temp.get(index).getDeparture(),
                                        temp.get(index).getArrival(), temp.get(index).getTicketAmount(), temp.get(index).getReservationCounter(), temp.get(index).getPrice() * temp.get(index).getTicketAmount(),
                                        date, time, temp.get(index).getTime()));
                                temp.remove(index);
                                accounts.get(accountKey).setReservedFlights(temp);
                                Intent createIntent = new Intent(CancelReservationLogin.this, MainMenu.class);
                                startActivity(createIntent);
                            }
                        });
                        flightDisplay.setNegativeButton("Cancel Cancelation", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder failedMessage = new AlertDialog.Builder(CancelReservationLogin.this);
                                failedMessage.setMessage("Cancelation has failed").setCancelable(false)
                                        .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent createIntent = new Intent(CancelReservationLogin.this, MainMenu.class);
                                                startActivity(createIntent);
                                            }
                                        });
                                failedMessage.show();
                            }
                        });
                        flightDisplay.show();
                    }
                }
                else if (errorCounter < 1 ) {
                    AlertDialog.Builder errorMessage = new AlertDialog.Builder(CancelReservationLogin.this);
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
                    AlertDialog.Builder errorMessage = new AlertDialog.Builder(CancelReservationLogin.this);
                    errorMessage.setMessage("Incorrect username and/or password").setCancelable(false)
                            .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent createIntent = new Intent(CancelReservationLogin.this, MainMenu.class);
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
            if (user.matches(accounts.get(i).getUsername()) && pass.matches(accounts.get(i).getPassword())) {
                accountKey = i;
                return true;
            }
        }
        return false;
    }
}
