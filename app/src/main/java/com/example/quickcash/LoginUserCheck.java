package com.example.quickcash;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/*
 * This class is used to validate login depending on whether the user exists in the database.
 * Provided the role, email and password, it first checks if the user exists in the specified role
 * node by searching for their email. If the email is found, it checks whether the password matches.
 * If everything matches, the login is validated, else appropriate error message is shown.
 */
public class LoginUserCheck {
    // Instance variables
    private Context context;
    private DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
    private String email;
    private String password;
    private String role;

    // Constructor
    public LoginUserCheck(Context context, String email, String password, String role) {
        this.context = context;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // This method is used to retrieve data from the database to validate login
    public void checkUserInFirebase() {
        // Get the node named by the role that stores users with that role
        dbRef.child(role).get().addOnCompleteListener(task -> {
            // If we found the role named node continue searching or else throw an error message
            if (task.isSuccessful()) {
                // Fetch data from the role node
                DataSnapshot snapshot = task.getResult();
                boolean found = false;

                // For each user in the role node fetch their email and password
                for (DataSnapshot users : snapshot.getChildren()) {
                    String dbEmail = users.child("email").getValue(String.class);
                    String dbPassword = users.child("password").getValue(String.class);

                    // If the email provided and the email fetched matches, the user is found
                    if (dbEmail != null && dbEmail.equalsIgnoreCase(email)) {
                        found = true;

                        // If the password provided and the password fetched matches, login is successful
                        if (dbPassword != null && dbPassword.equals(this.password)) {
                            // Redirect the user to the activity based on their role
                            if (role.equals("Employee")) {
                                Intent employee = new Intent(context, EmployeeActivity.class);
                                context.startActivity(employee);
                            } else if (role.equals("Employer")) {
                                Intent employer = new Intent(context, EmployerActivity.class);
                                context.startActivity(employer);
                            }
                            // Display the success message
                            Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                        // No need to search more users
                        break;
                    }
                }

                // If there exists no user with the provided email, display an error message
                if (!found) {
                    Toast.makeText(context, "User does not exist!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Error checking user!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}