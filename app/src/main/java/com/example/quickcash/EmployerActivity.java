package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EmployerActivity extends AppCompatActivity implements JobPostAdapter.OnItemClickListener {

    FirebaseCRUD firebaseCRUD;
    String jobPosterID;
    ImageButton createPostButton;

    RecyclerView recyclerView;
    JobPostAdapter jobPostAdapter;
    ArrayList<JobPost> jobPostList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Extract employerID.
        jobPosterID = getIntent().getStringExtra("jobPosterID");

        // Initialize the UI components.
        createPostButton = findViewById(R.id.createPostButton);
        createPostButton.setOnClickListener(view -> changeToJobPostingActivity());

        initializeFirebaseCRUD();
        setupRecyclerView();
        fetchJobPosts();
    }

    private void initializeFirebaseCRUD() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL);
        firebaseCRUD = new FirebaseCRUD(firebaseDatabase);
    }

    private void changeToJobPostingActivity() {
        Intent intent = new Intent(this, JobPostingActivity.class);
        intent.putExtra("jobPosterID", jobPosterID);
        startActivity(intent);
    }

    private void setupRecyclerView() {
        recyclerView = findViewById(R.id.employerRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        jobPostList = new ArrayList<>();
        jobPostAdapter = new JobPostAdapter(jobPostList, this); // Pass 'this' as the listener
        recyclerView.setAdapter(jobPostAdapter);
    }

    private void fetchJobPosts() {
        firebaseCRUD.getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jobPostList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    JobPost jobPost = postSnapshot.getValue(JobPost.class);
                    if (jobPost != null && jobPosterID.equals(jobPost.getJobPosterID())) {
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
        startActivity(intent);
    }
}