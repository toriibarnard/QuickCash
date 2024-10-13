package com.example.quickcash;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordReset extends AppCompatActivity {

    private FirebasePasswordReset firebasePasswordReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);  // Ensure the correct layout is used

        firebasePasswordReset = new FirebasePasswordReset();

        this.setupPasswordResetButton();
    }

    protected void setupPasswordResetButton() {
        Button resetPasswordButton = findViewById(R.id.sendButton);  // Updated to use sendButton ID
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = getEmailAddress();
                if (!email.isEmpty()) {
                    firebasePasswordReset.sendPasswordResetEmail(email, new FirebasePasswordReset.ResetPasswordCallback() {
                        @Override
                        public void onSuccess() {
                            Toast.makeText(PasswordReset.this, "Password reset email sent!", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(PasswordReset.this, "Failed to send reset email: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    Toast.makeText(PasswordReset.this, "Please enter your email address.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    protected String getEmailAddress() {
        EditText emailBox = findViewById(R.id.emailBox);  // Updated to use emailBox ID
        return emailBox.getText().toString().trim().toLowerCase();
    }
}
