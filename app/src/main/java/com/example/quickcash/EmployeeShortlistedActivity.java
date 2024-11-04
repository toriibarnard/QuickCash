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

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeShortlistedActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    RecyclerView recyclerView;
    String employeeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee_shortlisted);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // initialize the firebase authorization
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            employeeEmail = currentUser.getEmail();
        }

        FirebaseEmployeeApplicationInfo firebaseEmployeeApplicationInfo = new FirebaseEmployeeApplicationInfo(FirebaseDatabase.getInstance());

        // Initialize the RecyclerView and set LayoutManager
        recyclerView = findViewById(R.id.employeeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data asynchronously and set up RecyclerView once data is available
        firebaseEmployeeApplicationInfo.returnEmployeeShortlistedApplications(employeeEmail, applications -> {
            Log.d("EmployeeApplicationsActivity", "Number of applications loaded: " + applications.size());
            ApplicationAdapter applicationAdapter = new ApplicationAdapter(applications);
            recyclerView.setAdapter(applicationAdapter);
        });


        // make some buttons
        Button dashboardButton = findViewById(R.id.dashboardButton);

        // listner for dashboard button
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to employee Activity
                Intent intent = new Intent(EmployeeShortlistedActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });
    }

    // method to clear session data
    private void clearSessionData() {
        // clear Firebase session
        FirebaseAuth.getInstance().signOut();

        // clear SharedPreferences session ( the locally stored data)
        SharedPreferences preferences = getSharedPreferences("user_session", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();  // clear all data in the user_session file
        editor.apply();
    }
}
