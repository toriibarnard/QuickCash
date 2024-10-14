package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResetPasswordActivity extends AppCompatActivity {

    private FirebasePasswordReset firebasePasswordReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);  // Ensure the correct layout is used
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        firebasePasswordReset = new FirebasePasswordReset();

        this.setupPasswordResetButton();
    }

    protected void setupPasswordResetButton() {
        Button resetPasswordButton = findViewById(R.id.sendButton);  // Updated to use sendButton ID
        resetPasswordButton.setOnClickListener(view -> {
            String email = getEmailAddress();
            if (!email.isEmpty()) {
                firebasePasswordReset.sendPasswordResetEmail(email, new FirebasePasswordReset.ResetPasswordCallback() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(ResetPasswordActivity.this, "Password reset email sent!", Toast.LENGTH_LONG).show();
                        Intent login = new Intent(ResetPasswordActivity.this, LoginActivity.class);
                        ResetPasswordActivity.this.startActivity(login);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(ResetPasswordActivity.this, "Failed to send reset email: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(ResetPasswordActivity.this, "Please enter your email address.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected String getEmailAddress() {
        EditText emailBox = findViewById(R.id.resetEmailBox);  // Updated to use emailBox ID
        return emailBox.getText().toString().trim().toLowerCase();
    }
}
