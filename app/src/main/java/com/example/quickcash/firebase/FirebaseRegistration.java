package com.example.quickcash.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

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
    public void createAccount(final String name, final String email, final String phone, String password, final String role, final RegistrationCallback callback) {
        auth.createUserWithEmailAndPassword(email.toLowerCase(), password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = auth.getCurrentUser();
                        if (user != null) {
                            String uid = user.getUid();

                            // Prepare user data
                            HashMap<String, String> userData = new HashMap<>();
                            userData.put("name", name);
                            userData.put("email", email.toLowerCase());
                            userData.put("phone", phone);

                            // Send verification email
                            sendEmailVerification(user, new VerificationCallback() {
                                @Override
                                public void onSuccess() {
                                    // Notify the user that the email verification was sent
                                    callback.onAccountCreated();  // Inform user to check their email

                                    // Save the user data to the correct Firebase table based on role
                                    if (role.equalsIgnoreCase("employee")) {
                                        usersRef.child("employee").child(uid).setValue(userData)
                                                .addOnFailureListener(e -> callback.onFailure(e));
                                    } else if (role.equalsIgnoreCase("employer")) {
                                        usersRef.child("employer").child(uid).setValue(userData)
                                                .addOnFailureListener(e -> callback.onFailure(e));
                                    }
                                }

                                @Override
                                public void onFailure(Exception e) {
                                    // Handle failure in sending the verification email
                                    callback.onFailure(e);
                                }
                            });
                        } else {
                            callback.onFailure(new Exception("User is null."));
                        }
                    } else {
                        // Handle account creation failure
                        callback.onFailure(task.getException());
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

    // Callback interfaces
    public interface RegistrationCallback {
        void onAccountCreated();
        void onFailure(Exception e);
    }

    public interface VerificationCallback {
        void onSuccess();
        void onFailure(Exception e);
    }
}