package com.example.quickcash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class EmployeeApplicationsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

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

        // initialize the firebase authorization
        mAuth = FirebaseAuth.getInstance();

        // make some buttons
        Button dashboardButton = findViewById(R.id.dashboardButton);
        Button shortlistedButton = findViewById(R.id.shortlistedButton);

        // listener for dashboard button
        dashboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to employee Activity
                Intent intent = new Intent(EmployeeApplicationsActivity.this, EmployeeActivity.class);
                startActivity(intent);
            }
        });
        // listener for my applications button
        shortlistedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to employee Activity
                Intent intent = new Intent(EmployeeApplicationsActivity.this, EmployeeShortlistedActivity.class);
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
