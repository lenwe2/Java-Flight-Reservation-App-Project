package com.example.masterchiefs117.airlinereservationproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.masterchiefs117.airlinereservationproject.Account.accounts;
import static com.example.masterchiefs117.airlinereservationproject.Flight.flights;

/*
Title : MainMenu.java
Abtract: Activity that shows the main menu
Author: Nicholas Andrada
Date: 5/13/18
*/

public class MainMenu extends AppCompatActivity {

    private Button btnCreateAccount, btnReserveSeat, btnCancelReservation, btnManageSystem;
    private static boolean created = false;
    public static int reservationNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        if (!created) {
            createData();
        }

        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnReserveSeat = findViewById(R.id.btnReserveSeat);
        btnCancelReservation = findViewById(R.id.btnCancelReservation);
        btnManageSystem = findViewById(R.id.btnManageSystem);

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainMenu.this, CreateAccount.class);
                startActivity(createIntent);
            }
        });

        btnManageSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainMenu.this, ManageSystemLogin.class);
                startActivity(createIntent);
            }
        });

        btnReserveSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainMenu.this, ReserveSeat.class);
                startActivity(createIntent);
            }
        });
        btnCancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createIntent = new Intent(MainMenu.this, CancelReservationLogin.class);
                startActivity(createIntent);
            }
        });
    }

    public void createData() {
        accounts.put("!admiM2", new Account("!admiM2", "!admiM2"));
        accounts.put("A@lice5", new Account("A@lice5", "@cSit100"));
        accounts.put("$BriAn7", new Account("$BriAn7", "123aBc##"));
        accounts.put("!chriS12!", new Account("!chriS12!", "CHrIS12!!"));

        flights.add(new Flight("Otter101", "Monterey", "Los Angeles","10:30 AM", 10, 0, 150.00));
        flights.add(new Flight("Otter102", "Los Angeles", "Monterey", "1:00 PM",  10, 0, 150.00));
        flights.add(new Flight("Otter201", "Monterey", "Seattle", "11:00 AM",5, 0, 200.50));
        flights.add(new Flight("Otter205", "Monterey", "Seattle", "3:45 PM", 15, 0, 150.00));
        flights.add(new Flight("Otter202", "Seattle", "Monterey", "2:10 PM", 5, 0, 200.50));

        created = true;
    }
}
