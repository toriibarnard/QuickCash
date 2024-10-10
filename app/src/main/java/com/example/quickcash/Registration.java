package com.example.quickcash;

import static com.example.quickcash.R.layout.activity_registration;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Registration extends AppCompatActivity implements View.OnClickListener {

    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String FIREBASE_DATABASE = "https://quick-cash-64e58-default-rtdb.firebaseio.com/";
    FirebaseRegistration firebaseRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_registration);

        this.loadRoleSpinner();
        this.setupRegistrationButton();
        this.connectToDB();

        firebaseRegistration = new FirebaseRegistration();
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
        String email = getEmailAddress();
        String phoneNumber = getPhoneNumber();
        String name = getName();
        String password = getPassword();
        String role = getRole();
        String errorMessage = new String();
        CredentialValidator validator = new CredentialValidator();

        // verify all inputs are valid
        if(!validator.isValidPhone(phoneNumber)){
            errorMessage = getResources().getString(R.string.INVALID_PHONE_NUMBER).trim();
        } else if(!validator.isValidName(name)){
            errorMessage = getResources().getString(R.string.INVALID_NAME).trim();
        } else if(!validator.isValidEmail(email)){
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim();
        } else if(!validator.isValidPassword(password)){
            errorMessage = getResources().getString(R.string.INVALID_PASSWORD).trim();
        } else if(!validator.isValidRole(role)){
            errorMessage = getResources().getString(R.string.INVALID_ROLE).trim();
        }
        setStatusMessage(errorMessage);

        if(errorMessage.isEmpty()){

            firebaseRegistration.createAccount(name, email, phoneNumber, password, role, new FirebaseRegistration.RegistrationCallback() {
                @Override
                public void onAccountCreated() {
                    Toast.makeText(Registration.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess() {
                    Toast.makeText(Registration.this, "Account has been created!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(Registration.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            firebaseRegistration.verifyEmailAndAddUserToDatabase(name, email, phoneNumber,password, role,new FirebaseRegistration.RegistrationCallback() {
                @Override
                public void onAccountCreated() {
                }

                @Override
                public void onSuccess() {
                    Toast.makeText(Registration.this, "User successfully added to the database!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(Registration.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
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
        return emailBox.getText().toString().trim().toLowerCase();
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

}