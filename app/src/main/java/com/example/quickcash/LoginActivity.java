package com.example.quickcash;

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


        LoginValidator loginValidator = new LoginValidator();
        Spinner roleSelection = findViewById(R.id.roleSelectionSpinner);
        EditText emailAddress = findViewById(R.id.emailAddressEditField);
        EditText passwordField = findViewById(R.id.passwordEditField);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailAddress.getText().toString().trim();
                String password = passwordField.getText().toString().trim();
                String role = roleSelection.getSelectedItem().toString();

                if (!loginValidator.haveSelectedRole(role)) {
                    Toast.makeText(LoginActivity.this, "Please select a role", Toast.LENGTH_LONG).show();
                } else if (!loginValidator.isValidEmail(email)) {
                    Toast.makeText(LoginActivity.this, "Invalid email", Toast.LENGTH_LONG).show();
                } else if (!loginValidator.isValidPassword(password)) {
                    Toast.makeText(LoginActivity.this, "Invalid password", Toast.LENGTH_LONG).show();
                }

                LoginUserCheck loginUserCheck = new LoginUserCheck(email, password, role);
                loginUserCheck.checkUserInFirebase();
            }
        });
    }


}