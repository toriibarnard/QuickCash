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

        setLoginButton();
        setForgotPasswordButton();
        setCreateAccountButton();
    }

    public void handelLogin() {
        // Initializing objects and variable fields
        LoginValidator loginValidator = new LoginValidator();

        // Get the String values from each field
        String role = getSelectedRole();
        String email = getEmailAddress();
        String password = getPassword();
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
            LoginUserCheck loginUserCheck = new LoginUserCheck();
            loginUserCheck.checkUserInFirebase(LoginActivity.this, this, email, password, role);
        }

        // set the status label to the stored message
        setStatusMessage(errorMessage);
    }

    public void handelCreateAccount() {
        // Redirect to the Create Account page
        Intent createAccount = new Intent(LoginActivity.this, RegistrationActivity.class);
        LoginActivity.this.startActivity(createAccount);
    }

    public void handelForgotPassword() {
        // Redirect to the Reset Password page
        Intent resetPassword = new Intent(LoginActivity.this, ResetPasswordActivity.class);
        LoginActivity.this.startActivity(resetPassword);
    }

    public void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    public String getSelectedRole() {
        Spinner roleSelection = findViewById(R.id.roleSelectionSpinner);
        return roleSelection.getSelectedItem().toString().trim();
    }

    public String getEmailAddress() {
        EditText emailAddress = findViewById(R.id.emailAddressEditField);
        return emailAddress.getText().toString().trim();
    }

    public String getPassword() {
        EditText passwordField = findViewById(R.id.passwordEditField);
        return passwordField.getText().toString().trim();
    }

    public void setLoginButton() {
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> handelLogin());
    }

    public void setCreateAccountButton() {
        Button createAccount = findViewById(R.id.createAccountButton);
        createAccount.setOnClickListener(vie -> handelCreateAccount());
    }

    public void setForgotPasswordButton() {
        Button forgotPassword = findViewById(R.id.forgotPasswordButton);
        forgotPassword.setOnClickListener(view -> handelForgotPassword());
    }
}
