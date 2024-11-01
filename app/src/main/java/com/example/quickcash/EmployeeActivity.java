package com.example.quickcash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployeeActivity extends AppCompatActivity implements JobPostAdapter.OnItemClickListener {

    FirebaseCRUD firebaseCRUD;
    String jobSeekerID;

    RecyclerView recyclerView;
    JobPostAdapter jobPostAdapter;
    ArrayList<JobPost> jobPostList;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Extract employerID.
        jobSeekerID = getIntent().getStringExtra("jobSeekerID");

        // initialize the firebase authorization
        mAuth = FirebaseAuth.getInstance();

        initializeFirebaseCRUD();
        setupRecyclerView();
        fetchAllJobPosts();
        setupLogoutButton();
    }

    private void setupLogoutButton() {
        Button logoutButton = findViewById(R.id.logoutButton);
        Button applicationStatusButton = findViewById(R.id.applicationStatusButton);

        // set onClick listener for the logout button
        logoutButton.setOnClickListener(v -> {
            // log out
            mAuth.signOut();
            Toast.makeText(EmployeeActivity.this, "You have been logged out.", Toast.LENGTH_SHORT).show();

            // clear session data
            clearSessionData();

            // redirect to LoginActivity
            Intent intent = new Intent(EmployeeActivity.this, LoginActivity.class);
            startActivity(intent);

            // close the current activity
            finish();
        });

        // listner for my applications button
        applicationStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // redirect to Applications Activity
                Intent intent = new Intent(EmployeeActivity.this, EmployeeApplicationsActivity.class);
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

    private void initializeFirebaseCRUD() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseCRUD = new FirebaseCRUD(firebaseDatabase);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.employeeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobPostList = new ArrayList<>();
        jobPostAdapter = new JobPostAdapter(jobPostList, this); // Pass 'this' as the listener
        recyclerView.setAdapter(jobPostAdapter);
    }

    private void fetchAllJobPosts() {
        firebaseCRUD.getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobPostList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    JobPost jobPost = postSnapshot.getValue(JobPost.class);
                    if (jobPost != null) {
                        jobPostList.add(jobPost);
                    }
                }
                jobPostAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error fetching job posts: " + error.getMessage());
            }
        });
    }

    // Implement the interface method.
    @Override
    public void onViewDetailsClick(JobPost jobPost) {

        // Start JobDetailsActivity and pass the jobPost data.
        Intent intent = new Intent(this, JobDetailsActivity.class);
        intent.putExtra("jobPost", jobPost);
        intent.putExtra("role", "employee");
        startActivity(intent);
    }
}
