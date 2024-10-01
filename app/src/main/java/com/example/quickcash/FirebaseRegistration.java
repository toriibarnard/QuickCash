package com.example.quickcash;

import android.util.Log;
import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class FirebaseRegistration {

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference usersRef;

    public FirebaseRegistration() {
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        usersRef = database.getReference("users");
    }

    // Method to create a new account with name, email, phone, and password
    public void createAccount(final String name, final String email, final String phone, String password, final RegistrationCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Send email verification
                        sendEmailVerification(auth.getCurrentUser(), new VerificationCallback() {
                            @Override
                            public void onSuccess() {
                                Log.d("FirebaseRegistration", "Verification email sent.");
                                // User created but not yet added to the database until verification
                                callback.onAccountCreated();
                            }

                            @Override
                            public void onFailure(Exception e) {
                                Log.d("FirebaseRegistration", "Failed to send verification email.");
                                callback.onFailure(e);
                            }
                        });
                    } else {
                        // Throw exceptions for unique cases
                        Exception exception = task.getException();
                        if (exception instanceof FirebaseAuthWeakPasswordException) {
                            callback.onFailure(new Exception("Weak password."));
                        } else if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                            callback.onFailure(new Exception("Invalid email."));
                        } else if (exception instanceof FirebaseAuthUserCollisionException) {
                            callback.onFailure(new Exception("Email already in use."));
                        } else {
                            callback.onFailure(exception);
                        }
                    }
                });
    }

    // Method to send verification email
    private void sendEmailVerification(FirebaseUser user, final VerificationCallback callback) {
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            callback.onSuccess();
                        } else {
                            callback.onFailure(task.getException());
                        }
                    });
        } else {
            callback.onFailure(new Exception("User is null."));
        }
    }

    // Method to check if email is verified and add user to database
    public void verifyEmailAndAddUserToDatabase(final String name, final String email, final String phone, final RegistrationCallback callback) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            user.reload().addOnCompleteListener(task -> {
                if (user.isEmailVerified()) {
                    // Add user to database
                    String userId = user.getUid();
                    User userModel = new User(name, email, phone);
                    usersRef.child(userId).setValue(userModel).addOnCompleteListener(dbTask -> {
                        if (dbTask.isSuccessful()) {
                            callback.onSuccess();
                        } else {
                            callback.onFailure(dbTask.getException());
                        }
                    });
                } else {
                    callback.onFailure(new Exception("Email not verified."));
                }
            });
        } else {
            callback.onFailure(new Exception("No user logged in."));
        }
    }

    // Callback interfaces
    public interface RegistrationCallback {
        void onAccountCreated();
        void onSuccess();
        void onFailure(Exception e);
    }

    public interface VerificationCallback {
        void onSuccess();
        void onFailure(Exception e);
    }

    // User model class
    public static class User {
        public String name;
        public String email;
        public String phone;

        public User() { }

        public User(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }
    }
}