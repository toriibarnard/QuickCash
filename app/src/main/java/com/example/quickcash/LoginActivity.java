package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// This class controls the behaviour of login user interface
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initializing objects and variable fields
        LoginValidator loginValidator = new LoginValidator();
        Spinner roleSelection = findViewById(R.id.roleSelectionSpinner);
        EditText emailAddress = findViewById(R.id.emailAddressEditField);
        EditText passwordField = findViewById(R.id.passwordEditField);
        Button loginButton = findViewById(R.id.loginButton);
        Button forgotPassword = findViewById(R.id.forgotPasswordButton);

        // Actions to perform when "Login" button is clicked
        loginButton.setOnClickListener(view -> {
            // Get the String values from each field
            String role = roleSelection.getSelectedItem().toString().trim();
            String email = emailAddress.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String errorMessage = "";

            // Ensure all fields have valid values and validate login
            if (!loginValidator.haveSelectedRole(role)) {
                errorMessage = getResources().getString(R.string.INVALID_ROLE);
            } else if (!loginValidator.isValidEmail(email)) {
                errorMessage = getResources().getString(R.string.INVALID_EMAIL);
            } else if (!loginValidator.isValidPassword(password)) {
                errorMessage = getResources().getString(R.string.INVALID_PASSWORD);
            } else {
                // Call checkUserInFirebase() method from LoginUserCheck class to authenticate login
                LoginUserCheck loginUserCheck = new LoginUserCheck(LoginActivity.this, this, email, password, role);
                loginUserCheck.checkUserInFirebase();
            }

            // set the status label to the stored message
            setStatusMessage(errorMessage);
        });

        // Actions to perform when "Forgot Password" password button is clicked
        forgotPassword.setOnClickListener(view -> {
            // Redirect to the Reset password page
            Intent resetPassword = new Intent(LoginActivity.this, ResetPasswordActivity.class);
            LoginActivity.this.startActivity(resetPassword);
        });
    }

    // This method is used to set the status label to a given message
    public void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

}
