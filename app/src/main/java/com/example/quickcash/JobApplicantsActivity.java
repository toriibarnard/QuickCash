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

    RecyclerView applicantsRecyclerView; // recycler view to display list of applicants
    ApplicantAdapter applicantAdapter; // adapter to bind applicants to recycler view
    ArrayList<Applicant> applicantList; // list to hold applicants
    FirebaseCRUD firebaseCRUD;
    String jobID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_applicants);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // get the jobID passed from JobDetailsActivity
        jobID = getIntent().getStringExtra("jobID");

        if (jobID == null) {
            // no jobID is passed
            Toast.makeText(this, "No job ID provided", Toast.LENGTH_SHORT).show();
            finish(); // end activity
            return;
        }

        // jobID is received confirmation message
        Toast.makeText(this, "Viewing applicants for Job ID: " + jobID, Toast.LENGTH_SHORT).show();

        initializeFirebaseCRUD();
        setupRecyclerView(); // initialize RecyclerView

        fetchApplicants(); // fetch the applicants for the specific jobID
    }

    // initialize the firebaseCRUD helper class
    private void initializeFirebaseCRUD() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance(Constants.FIREBASE_DATABASE_URL);
        firebaseCRUD = new FirebaseCRUD(firebaseDatabase);
    }

    // setup the RecyclerView to display the applicants
    private void setupRecyclerView() {
        // find the RecyclerView in the layout and set its layout manager to a LinearLayout
        applicantsRecyclerView = findViewById(R.id.applicantsRecyclerView);
        applicantsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initialize the list of applicants and set up the adapter
        applicantList = new ArrayList<>();
        applicantAdapter = new ApplicantAdapter(applicantList, this);  // pass 'this' as listener
        applicantsRecyclerView.setAdapter(applicantAdapter); // set the adapter to the RecyclerView
    }

    // fetch applicants from Firebase for the current jobID
    private void fetchApplicants() {
        firebaseCRUD.getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                applicantList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    Applicant applicant = postSnapshot.getValue(Applicant.class);
                    if (applicant != null && jobID.equals(applicant.getApplicantJobID())) {
                        // add applicants who applied for the specific jobID
                        applicantList.add(applicant);
                    }
                }

                if (applicantList.isEmpty()) {
                    // no applicants found
                    Toast.makeText(JobApplicantsActivity.this, "No applicants found for this job", Toast.LENGTH_SHORT).show();
                }

                applicantAdapter.notifyDataSetChanged(); // update the RecyclerView
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(JobApplicantsActivity.this, "Error fetching applicants: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // method to handle clicks on applicants in the RecyclerView
    @Override
    public void onViewApplicantClick(Applicant applicant) {
        // start ApplicantDetailsActivity and pass the applicants email
        Intent intent = new Intent(this, ApplicantDetailsActivity.class);
        intent.putExtra("applicantEmail", applicant.getApplicantEmail());
        startActivity(intent);
    }
}