package com.example.quickcash;

import static com.example.quickcash.R.layout.activity_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseRegistration firebaseRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_registration);

        this.setupRegistrationButton();
        firebaseRegistration = new FirebaseRegistration();
    }

    protected void setupRegistrationButton() {
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
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
        RegistrationValidator validator = new RegistrationValidator();

        // verify all inputs are valid
        if (!validator.isValidName(name)) {
            errorMessage = getResources().getString(R.string.INVALID_NAME).trim();
        } else if(!validator.isValidPhone(phoneNumber)){
            errorMessage = getResources().getString(R.string.INVALID_PHONE_NUMBER).trim();
        } else if(!validator.isValidEmail(email)){
            errorMessage = getResources().getString(R.string.INVALID_EMAIL).trim();
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
                    Toast.makeText(RegistrationActivity.this, "Check your email to verify your account", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess() {
                    Toast.makeText(RegistrationActivity.this, "Account has been created!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(RegistrationActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

            firebaseRegistration.verifyEmailAndAddUserToDatabase(name, email, phoneNumber,password, role,new FirebaseRegistration.RegistrationCallback() {
                @Override
                public void onAccountCreated() {
                }

                @Override
                public void onSuccess() {
                    Toast.makeText(RegistrationActivity.this, "User successfully added to the database!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Exception e) {
                    Toast.makeText(RegistrationActivity.this, "Verification failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            // Redirect user to the login page
            Intent loginActivity = new Intent(RegistrationActivity.this, LoginActivity.class);
            RegistrationActivity.this.startActivity(loginActivity);
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