package com.example.quickcash;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String FIREBASE_DATABASE = "https://quick-cash-64e58-default-rtdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.loadRoleSpinner();
        this.setupRegistrationButton();
        this.connectToDB();
    }

    protected void loadRoleSpinner() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("select your role");
        roles.add("employer");
        roles.add("employee");

        // Use built-in android layout resource
        ArrayAdapter<String> roleAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                roles
        );
        roleAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        roleSpinner.setAdapter(roleAdapter);
    }

    protected void setupRegistrationButton() {
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
    }

    private void connectToDB() {
        database = FirebaseDatabase.getInstance(FIREBASE_DATABASE);
        databaseReference = database.getReference("message");
    }

    @Override
    public void onClick(View view) {
        // to be implemented
    }

    protected String getPhoneNumber() {
        EditText phoneBox = findViewById(R.id.phoneBox);
        return phoneBox.getText().toString().trim();
    }

    protected String getName() {
        EditText nameBox = findViewById(R.id.nameBox);
        return nameBox.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailBox = findViewById(R.id.emailBox);
        return emailBox.getText().toString().trim();
    }

    protected String getPassword() {
        EditText passwordBox = findViewById(R.id.passwordBox);
        return passwordBox.getText().toString().trim();
    }

    protected String getRole() {
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        return roleSpinner.getSelectedItem().toString().trim();
    }

    protected void setStatusMessage(String message) {
        // modify to AT specifications
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected void saveInfoToFirebase(String bannerID, String emailAddress, String role) {
        // to be implemented
    }
}