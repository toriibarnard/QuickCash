package com.example.quickcash;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginUserCheck {
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private String email;
    private String password;
    private String role;


    public LoginUserCheck(String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void checkUserInFirebase() {
        dbRef.child(role).orderByChild("email").equalTo(email).get().addOnCompleteListener(task -> {

        });
    }

}
