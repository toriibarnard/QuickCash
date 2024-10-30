package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JobDetailsActivity extends AppCompatActivity {

    JobPost jobPost;

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

        setUpApplyButton();

        // Get the jobPost from the intent.
        this.jobPost = getIntent().getSerializableExtra("jobPost", JobPost.class);

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

    // Set apply button only for an employee. Employer cannot apply to a job
    protected void setUpApplyButton() {
        String role = getIntent().getStringExtra("role");
        if (role != null && role.equals("employee")) {
            Button applyButton = findViewById(R.id.applyButton);
            applyButton.setVisibility(View.VISIBLE);
            applyButton.setOnClickListener(view -> handelApplication());
        }
    }

    protected void handelApplication() {
        Intent apply = new Intent(JobDetailsActivity.this, JobApplicationActivity.class);
        apply.putExtra("jobId", jobPost.getJobID());
        apply.putExtra("jobTitle", jobPost.getJobTitle());
        JobDetailsActivity.this.startActivity(apply);
    }
}
