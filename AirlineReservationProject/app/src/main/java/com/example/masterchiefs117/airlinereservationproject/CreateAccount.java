package com.example.masterchiefs117.airlinereservationproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.masterchiefs117.airlinereservationproject.Account.accounts;
import static com.example.masterchiefs117.airlinereservationproject.Transaction.transactions;

/*
Title : CreateAccount.java
Abtract: Activity that lets you create an account
Author: Nicholas Andrada
Date: 5/13/18
*/
public class CreateAccount extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button btnAccountCreation;
    public int errorCounter = 0;
    public boolean valid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        editTextUsername = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnAccountCreation = findViewById(R.id.btnAccountCreation);
        btnAccountCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification(editTextUsername.getText().toString(), editTextPassword.getText().toString());
            }
        });
    }

    public void verification(String user, String pass) {
        if (errorCounter > 1) {
            valid = true;
        }
        if (usernameExists(user)) {
            AlertDialog.Builder errorMessage = new AlertDialog.Builder(this);
            errorMessage.setMessage("Username already exists in the system").setCancelable(false)
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
        else if(user.matches(".*[a-z].*") && user.matches(".*[A-Z].*") && user.matches(".*[0-9].*")
                && (user.matches(".*[!-$].*") || user.contains("@"))){
            if(pass.matches(".*[a-z].*") && pass.matches(".*[A-Z].*") && pass.matches(".*[0-9].*")
                    && (pass.matches(".*[!-$].*") || pass.contains("@"))) {
                accounts.put(user, new Account(user, pass));
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                String date = DateFormat.getDateInstance().format(calendar.getTime());
                String time = format.format(calendar.getTime());
                transactions.add(new Transaction("New Account", user, "", "", "", 0, 0, 0,  date, time,""));
                AlertDialog.Builder successMessage = new AlertDialog.Builder(this);
                successMessage.setMessage("Account has been created successfully").setCancelable(false)
                        .setNeutralButton("Confirm", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        });
                AlertDialog success = successMessage.create();
                success.setTitle("Success");
                success.show();
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

    public boolean usernameExists(String user) {
        for(String i: accounts.keySet() ) {
            if (i.equals(user)) {
                return true;
            }
        }
        return false;
    }


}
