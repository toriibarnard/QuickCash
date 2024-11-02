package com.example.quickcash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EmployeeApplicationsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    RecyclerView recyclerView;
    String employeeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_applications);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase authorization
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            employeeEmail = currentUser.getEmail();
        }

        // Initialize FirebaseEmployeeApplicationInfo
        FirebaseEmployeeApplicationInfo firebaseEmployeeApplicationInfo = new FirebaseEmployeeApplicationInfo(FirebaseDatabase.getInstance());

        // Initialize the RecyclerView and set LayoutManager
        recyclerView = findViewById(R.id.employeeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data asynchronously and set up RecyclerView once data is available
        firebaseEmployeeApplicationInfo.returnEmployeeApplications(employeeEmail, applications -> {
            Log.d("EmployeeApplicationsActivity", "Number of applications loaded: " + applications.size());
            ApplicationAdapter applicationAdapter = new ApplicationAdapter(applications);
            recyclerView.setAdapter(applicationAdapter);
        });

        // Setup buttons and listeners
        Button dashboardButton = findViewById(R.id.dashboardButton);
        Button shortlistedButton = findViewById(R.id.shortlistedButton);

        dashboardButton.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeApplicationsActivity.this, EmployeeActivity.class);
            startActivity(intent);
        });

        shortlistedButton.setOnClickListener(v -> {
            Intent intent = new Intent(EmployeeApplicationsActivity.this, EmployeeShortlistedActivity.class);
            startActivity(intent);
        });
    }

    // Method to clear session data
    private void clearSessionData() {
        // Clear Firebase session
        FirebaseAuth.getInstance().signOut();

        // Clear SharedPreferences session (the locally stored data)
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();  // Clear all data in the user_session file
        editor.apply();
    }
}
