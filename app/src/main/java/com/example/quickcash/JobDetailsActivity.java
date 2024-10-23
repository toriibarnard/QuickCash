package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JobDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_details_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // retrieve the JobPost object from the intent
        JobPost jobPost = (JobPost) getIntent().getSerializableExtra("jobPost");

        if (jobPost == null) {
            // handle case if jobPost is not passed correctly
            Toast.makeText(this, "Job details not available", Toast.LENGTH_SHORT).show();
            finish();  // close activity if no job details are available
            return;
        }

        // Set the job details in the TextViews
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

        // initialize the view applicants button
        Button viewApplicantsButton = findViewById(R.id.viewApplicantsButton);

        // set the OnClickListener to navigate to JobApplicantsActivity
        viewApplicantsButton.setOnClickListener(v -> {
            String jobIDStr = jobPost.getJobID();  // retrieve the jobID from the jobPost object

            if (jobIDStr != null) {
                // pass the jobID to JobApplicantsActivity
                Intent intent = new Intent(JobDetailsActivity.this, JobApplicantsActivity.class);
                intent.putExtra("jobID", jobIDStr);  // pass jobID to the next activity
                startActivity(intent);
            } else {
                Toast.makeText(JobDetailsActivity.this, "No Job ID available", Toast.LENGTH_SHORT).show();
            }
        });
    }
}