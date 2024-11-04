package com.example.quickcash.firebase;

import androidx.annotation.NonNull;

import com.example.quickcash.util.employeeView.ApplicationData;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirebaseEmployeeApplicationInfo {

    private final DatabaseReference databaseReference;
    private Map<String, ApplicationData> cachedApplications;

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    /**
     * Constructor initializes the database reference and the cache.
     * It sets up a listener to keep the cache updated with any changes in the database.
     *
     * @param firebaseDatabase the FirebaseDatabase instance.
     */
    public FirebaseEmployeeApplicationInfo(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("applications");
        this.cachedApplications = new HashMap<>();
        initializeCache();
    }

    /**
     * Initializes the cache by setting up a listener on the database reference.
     * The cache is updated whenever data changes in the "applications" node.
     */

    private void initializeCache() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cachedApplications.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String applicationId = postSnapshot.getKey();
                    String jobID = postSnapshot.child("jobId").getValue(String.class);
                    String applicationStatus = postSnapshot.child("applicantStatus").getValue(String.class);
                    String applicantEmail = postSnapshot.child("applicantEmail").getValue(String.class);
                    String applicantPhone = postSnapshot.child("applicantPhone").getValue(String.class);
                    String applicantName = postSnapshot.child("applicantName").getValue(String.class);
                    ApplicationData jobApplication = new ApplicationData(applicationId, applicantEmail, applicantPhone, applicantName, applicationStatus, jobID);
                    cachedApplications.put(applicationId, jobApplication);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error updating cache: " + error.getMessage());
            }
        });
    }

    public interface OnApplicationsLoadedListener {
        void onApplicationsLoaded(ArrayList<ApplicationData> applications);
    }

    /**
     * Return all job applications for a given employee
     * Search applications by employee email
     */
    public void returnEmployeeApplications(String employeeEmail, OnApplicationsLoadedListener listener) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ApplicationData> employeeApplications = new ArrayList<>();
                for (ApplicationData application : cachedApplications.values()) {
                    if (employeeEmail.equals(application.getApplicantEmail())) {
                        employeeApplications.add(application);
                    }
                }
                listener.onApplicationsLoaded(employeeApplications);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error loading applications: " + error.getMessage());
            }
        });
    }

    public void returnEmployeeShortlistedApplications(String employeeEmail, OnApplicationsLoadedListener listener) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ApplicationData> employeeApplications = new ArrayList<>();
                for (ApplicationData application : cachedApplications.values()) {
                    if (employeeEmail.equals(application.getApplicantEmail()) && application.getApplicationStatus().equals("Shortlisted")) {
                        employeeApplications.add(application);
                    }
                }
                listener.onApplicationsLoaded(employeeApplications);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error loading applications: " + error.getMessage());
            }
        });
    }
}
