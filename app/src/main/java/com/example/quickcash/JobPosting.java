package com.example.quickcash;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.UUID;


public class JobPosting extends AppCompatActivity {

    private String jobId;
    private String jobTitle;
    private String jobCompany;
    private String jobDescription;
    private String jobLocation;
    private String jobType;
    private String experienceLevel;
    private String industry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_posting);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public JobPosting(){
        this.jobId = generateJobID(); // Generate a unique ID
        this.jobTitle = "";
        this.jobCompany = "";
        this.jobDescription = "";
        this.jobLocation = "";
        this.jobType = "";
        this.experienceLevel = "";
        this.industry = "";
    }

    public JobPosting(String jobTitle, String jobCompany, String jobDescription, String jobLocation,
                      String jobType, String experienceLevel, String industry) {
        this.jobId = generateJobID();// Generate a unique ID
        this.jobTitle = jobTitle;
        this.jobCompany = jobCompany;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.jobType = jobType;
        this.experienceLevel = experienceLevel;
        this.industry = industry;
    }

    public String generateJobID(){
        return UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobCompany() {
        return jobCompany;
    }

    public void setJobCompany(String jobCompany) {
        this.jobCompany = jobCompany;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobLocation() {
        return jobLocation;
    }

    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(String experienceLevel) {
        this.experienceLevel = experienceLevel;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public boolean isJobLocationValid(String location) {
        // Format: City, State, Country
        String locationRegex = "^[a-zA-Z\\s]+,\\s*[a-zA-Z\\s]+,\\s*[a-zA-Z\\s]+$";

        return location.matches(locationRegex);
    }
}