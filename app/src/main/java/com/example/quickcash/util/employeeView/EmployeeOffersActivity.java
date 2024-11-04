package com.example.quickcash.util.employeeView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.util.employerView.Applicant;
import com.example.quickcash.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeOffersActivity extends AppCompatActivity implements OfferAdapter.OnOfferClickListener {

    private RecyclerView offersRecyclerView;
    private OfferAdapter offerAdapter;
    private List<Applicant> offerList;
    private DatabaseReference applicationsRef;
    private String employeeEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_offers);

        // initialize RecyclerView
        offersRecyclerView = findViewById(R.id.offersRecyclerView);
        offersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initialize firebase reference
        applicationsRef = FirebaseDatabase.getInstance().getReference("applications");

        // get current employee email from firebase auth
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            employeeEmail = currentUser.getEmail();
        } else {
            Toast.makeText(this, "No employee email found. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // initialize offer list and adapter
        offerList = new ArrayList<>();
        offerAdapter = new OfferAdapter(offerList, this);
        offersRecyclerView.setAdapter(offerAdapter);

        // fetch job offers from the database
        fetchJobOffers();
    }

    // method to fetch job offers
    private void fetchJobOffers() {
        applicationsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                offerList.clear();

                for (DataSnapshot applicationSnapshot : snapshot.getChildren()) {
                    Applicant applicant = applicationSnapshot.getValue(Applicant.class);

                    if (applicant == null) {
                        Log.e("EmployeeOffersActivity", "Applicant data is null.");
                        continue;
                    }

                    // set applicationId for the applicant
                    applicant.setApplicationId(applicationSnapshot.getKey());

                    if (applicant.getApplicantEmail() != null && applicant.getApplicantEmail().equals(employeeEmail)
                            && "Pending".equals(applicant.getApplicantStatus())) {

                        offerList.add(applicant);
                    }
                }

                if (offerList.isEmpty()) {
                    Toast.makeText(EmployeeOffersActivity.this, "No job offers available.", Toast.LENGTH_SHORT).show();
                } else {
                    offerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("EmployeeOffersActivity", "Error fetching offers: " + error.getMessage());
                Toast.makeText(EmployeeOffersActivity.this, "Error loading offers.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAcceptClick(Applicant offer) {
        if (offer.getApplicationId() == null) {
            Toast.makeText(this, "Offer data is invalid. Please try again later.", Toast.LENGTH_SHORT).show();
            return;
        }

        applicationsRef.child(offer.getApplicationId()).child("applicantStatus").setValue("Hired")
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Offer accepted.", Toast.LENGTH_SHORT).show();
                    fetchJobOffers(); // refresh the list after acceptance
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Failed to accept offer.", Toast.LENGTH_SHORT).show();
                    Log.e("EmployeeOffersActivity", "Failed to update status", e);
                });
    }

    @Override
    public void onRejectClick(Applicant offer) {
        if (offer.getApplicationId() == null) {
            Toast.makeText(this, "Offer data is invalid. Please try again later.", Toast.LENGTH_SHORT).show();
            return;
        }

        applicationsRef.child(offer.getApplicationId()).child("applicantStatus").setValue("Rejected")
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Offer rejected.", Toast.LENGTH_SHORT).show();
                    fetchJobOffers(); // refresh the list after rejection
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to reject offer.", Toast.LENGTH_SHORT).show());
    }
}