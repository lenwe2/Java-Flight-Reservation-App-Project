package com.example.masterchiefs117.airlinereservationproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import static com.example.masterchiefs117.airlinereservationproject.Account.accounts;
import static com.example.masterchiefs117.airlinereservationproject.Transaction.transactions;

/*
Title : ManageSystemLogin.java
Abtract: Activity that shows you the transactions and asks if you want to add a new flight
Author: Nicholas Andrada
Date: 5/13/18
*/
public class ManageSystemLogin extends AppCompatActivity {

    private EditText editTextUser, editTextPass;
    private Button btnLogin;
    public int errorCounter = 0;
    public boolean valid = false;
    private String [] transactionList;

    // Need to fix the log in for the admin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        editTextUser = findViewById(R.id.editTextUser);
        editTextPass = findViewById(R.id.editTextPass);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification(editTextUser.getText().toString(), editTextPass.getText().toString());
            }
        });
    }

    public void verification(String user, String pass) {
        if (errorCounter >= 2) {
            valid = true;
        }
        if(user.equals(accounts.get("!admiM2").getUsername()) && pass.equals(accounts.get("!admiM2").getPassword())) {
            if (transactions.isEmpty()) {
                AlertDialog.Builder message = new AlertDialog.Builder(this);
                message.setMessage("No log information at the moment");
                message.setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder addFlightMessage = new AlertDialog.Builder(ManageSystemLogin.this);
                        addFlightMessage.setTitle("Add New Flight");
                        addFlightMessage.setMessage("Would you like to add a new flight?");
                        addFlightMessage.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent createIntent = new Intent(ManageSystemLogin.this, AddNewFlight.class);
                                startActivity(createIntent);
                            }
                        });
                        addFlightMessage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent createIntent = new Intent(ManageSystemLogin.this, MainMenu.class);
                                startActivity(createIntent);
                            }
                        });
                        addFlightMessage.show();
                    }
                });
            }
            else {
                transactionList = new String[transactions.size()];
                NumberFormat formatter = new DecimalFormat("#0.00");
                for (int i = 0; i < transactions.size(); i++) {
                    Transaction t = transactions.get(i);
                    if (t.getTransactionType().matches("New Account")) {
                        transactionList[i] = "Transaction Type: " + t.getTransactionType() + "\n" + "Username: " + t.getUsername() + "\n" + t.getCurrentDate() + "\n" + t.getCurrentTime() + "\n";
                    } else if (t.getTransactionType().matches("Reserve Seat")) {
                        transactionList[i] = "Transaction Type: " + t.getTransactionType() + "\n" + "Username: " + t.getUsername() + "\n" + "Flight Number: " + t.getFlightNumber() + "\n" +
                                "Departure: " + t.getDeparture() + "\n" + "Arrival: " + t.getArrival() + "\n" + "Departure Time: " + t.getTime()
                                + "\n" + "Reservation Number: " + t.getReservationNumber() + "\n" + "Total Amount: $" + formatter.format(t.getPrice()) + "\n" + t.getCurrentDate() + "\n" + t.getCurrentTime() + "\n";
                    } else if (t.getTransactionType().matches("Cancel Reservation")) {
                        transactionList[i] = "Transaction Type: " + t.getTransactionType() + "\n" + "Username: " + t.getUsername() + "\n" + "Flight Number: " + t.getFlightNumber() + "\n" +
                                "Departure: " + t.getDeparture() + "\n" + "Arrival: " + t.getArrival() + "\n" + "Departure Time: " + t.getTime()
                                + "\n" + "Reservation Number: " + t.getReservationNumber() + "\n" + "Number of Reserved Tickets: " + t.getTickets() + "\n" + t.getCurrentDate() + "\n" + t.getCurrentTime() + "\n";
                    }
                }
                AlertDialog.Builder transactionMessage = new AlertDialog.Builder(this);
                transactionMessage.setTitle("Transactions");
                transactionMessage.setItems(transactionList, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                });
                transactionMessage.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog.Builder addFlightMessage = new AlertDialog.Builder(ManageSystemLogin.this);
                        addFlightMessage.setTitle("Add New Flight");
                        addFlightMessage.setMessage("Would you like to add a new flight?");
                        addFlightMessage.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent createIntent = new Intent(ManageSystemLogin.this, AddNewFlight.class);
                                startActivity(createIntent);
                            }
                        });
                        addFlightMessage.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent createIntent = new Intent(ManageSystemLogin.this, MainMenu.class);
                                startActivity(createIntent);
                            }
                        });
                        addFlightMessage.show();
                    }
                });
                transactionMessage.show();
            }
        }
        else {
            AlertDialog.Builder errorMessage = new AlertDialog.Builder(this);
            errorMessage.setMessage("Username and/or password are not in the correct format").setCancelable(false)
                    .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            errorCounter++;
                            if (valid) {
                                finish();
                            }
                        }
                    });
            AlertDialog error = errorMessage.create();
            error.setTitle("Error");
            error.show();
        }
    }
}
