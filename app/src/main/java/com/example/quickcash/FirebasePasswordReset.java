package com.example.quickcash;

import com.google.firebase.auth.FirebaseAuth;

public class FirebasePasswordReset {

    private FirebaseAuth auth;


    public FirebasePasswordReset() {
        auth = FirebaseAuth.getInstance();
    }

    // Method to send password reset email
    public void sendPasswordResetEmail(String email, final ResetPasswordCallback callback) {
        auth.sendPasswordResetEmail(email.toLowerCase()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                callback.onSuccess();  // Notify that email has been sent
            } else {
                callback.onFailure(task.getException());  // Notify of failure
            }
        });
    }

    // Callback interface for password reset
    public interface ResetPasswordCallback {
        void onSuccess();
        void onFailure(Exception e);
    }
}
