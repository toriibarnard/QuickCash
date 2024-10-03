package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the String values from each field
                String email = emailAddress.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                String role = roleSelection.getSelectedItem().toString();

                // Ensure all fields have valid values and validate login
                if (!loginValidator.haveSelectedRole(role)) {
                    Toast.makeText(LoginActivity.this, "Please select a role", Toast.LENGTH_LONG).show();
                } else if (!loginValidator.isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
                } else if (!loginValidator.isValidPassword(password)) {
                    Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_LONG).show();
                } else {
                    // Call checkUserInFirebase() method from LoginUserCheck class to authenticate login
                    LoginUserCheck loginUserCheck = new LoginUserCheck(LoginActivity.this, email, password, role);
                    loginUserCheck.checkUserInFirebase();
                }
            }
        });

        // Actions to perform when "Forgot Password" password button is clicked
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to the Reset password page
                Intent resetPassword = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                LoginActivity.this.startActivity(resetPassword);
            }
        });
    }

}
