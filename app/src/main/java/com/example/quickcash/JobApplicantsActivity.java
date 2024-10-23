package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

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

public class JobApplicantsActivity extends AppCompatActivity implements ApplicantAdapter.OnItemClickListener {

    RecyclerView applicantsRecyclerView;
    ApplicantAdapter applicantAdapter;
    ArrayList<Applicant> applicantList;
    FirebaseCRUD firebaseCRUD;
    String jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_applicants);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get jobID passed from JobDetailsActivity
        jobID = getIntent().getStringExtra("jobID");

        initializeFirebaseCRUD();
        setupRecyclerView();
        fetchApplicants();
    }

    private void initializeFirebaseCRUD() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL);
        firebaseCRUD = new FirebaseCRUD(firebaseDatabase);
    }

    private void setupRecyclerView() {
        applicantsRecyclerView = findViewById(R.id.applicantsRecyclerView);
        applicantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        applicantList = new ArrayList<>();
        applicantAdapter = new ApplicantAdapter(applicantList, this);  // Pass 'this' as listener
        applicantsRecyclerView.setAdapter(applicantAdapter);
    }

    private void fetchApplicants() {
        firebaseCRUD.getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                applicantList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Applicant applicant = postSnapshot.getValue(Applicant.class);
                    if (applicant != null && jobID.equals(applicant.getApplicantJobID())) {
                        applicantList.add(applicant);
                    }
                }
                applicantAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error fetching applicants: " + error.getMessage());
            }
        });
    }

    // Implement the interface method for view applicant click
    @Override
    public void onViewApplicantClick(Applicant applicant) {
        // Start ApplicantDetailsActivity and pass the applicant's email
        Intent intent = new Intent(this, ApplicantDetailsActivity.class);
        intent.putExtra("applicantEmail", applicant.getApplicantEmail());
        startActivity(intent);
    }
}