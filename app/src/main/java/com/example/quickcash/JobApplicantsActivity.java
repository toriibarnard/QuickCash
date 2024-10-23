package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JobApplicantsActivity extends AppCompatActivity {

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
        // initialize the view applicants button
        Button viewApplicantsButton = findViewById(R.id.viewApplicantsButton);

        // set OnClickListener to handle navigation to JobApplicantsActivity
        viewApplicantsButton.setOnClickListener(v -> {
            Intent intent = new Intent(JobApplicantsActivity.this, JobApplicantsActivity.class);
            intent.putExtra("jobID", getIntent().getStringExtra("jobID")); // Pass jobID to the next activity if needed
            startActivity(intent);
        });

        // Get the jobPost from the intent.
        JobPost jobPost = getIntent().getSerializableExtra("jobPost", JobPost.class);

        TextView jobID = findViewById(R.id.jobIDDetails);
        jobID.setText(jobPost.getJobID());

        TextView jobDetailsTitle = findViewById(R.id.jobDetailsTitle);
        jobDetailsTitle.setText(jobPost.getJobTitle());

        TextView companyName = findViewById(R.id.companyNameDetails);
        companyName.setText(jobPost.getCompanyName());

        TextView jobDescription = findViewById(R.id.jobDescriptionDetails);
        jobDescription.setText(jobPost.getJobDescription());

        TextView jobType = findViewById(R.id.jobTypeDetails);
        jobType.setText(jobPost.getJobType());

        TextView experienceLevel = findViewById(R.id.experienceLevelDetails);
        experienceLevel.setText(jobPost.getExperienceLevel());

        TextView industry = findViewById(R.id.industryDetails);
        industry.setText(jobPost.getIndustry());

        TextView location = findViewById(R.id.locationDetails);
        location.setText(jobPost.getLocation());

        TextView postedDate = findViewById(R.id.postedDateDetails);
        postedDate.setText(jobPost.getPostedDate());
    }

}