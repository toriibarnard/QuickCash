package com.example.quickcash;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginUserCheck {
    private Context context;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private String email;
    private String password;
    private String role;


    public LoginUserCheck(Context context, String email, String password, String role) {
        this.context = context;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public void checkUserInFirebase() {
        dbRef.child(role).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                boolean found = false;

                for (DataSnapshot users : snapshot.getChildren()) {
                    String dbEmail = users.child("email").getValue(String.class);
                    String dbPassword = users.child("password").getValue(String.class);

                    if (dbEmail != null && dbEmail.equals(email)) {
                        found = true;

                        if (dbPassword != null && dbPassword.equals(this.password)) {
                            if (role.equals("Employee")) {
                                Intent employee = new Intent(context, EmployeeActivity.class);
                                context.startActivity(employee);
                            } else if (role.equals("Employer")) {
                                Intent employer = new Intent(context, EmployerActivity.class);
                                context.startActivity(employer);
                            }
                            Toast.makeText(context, "Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    }
                }

                if (!found) {
                    Toast.makeText(context, "User does not exist!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Error checking user!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
