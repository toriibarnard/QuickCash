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

public class ApplicantDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applicant_details_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // get the jobPost from the intent.
        Applicant applicant = getIntent().getSerializableExtra("applicant", Applicant.class);

        TextView applicantJobID = findViewById(R.id.applicantJobIDDetails);
        applicantJobID.setText(applicant.getApplicantJobID());

        TextView applicantName = findViewById(R.id.applicantDetailsTitle);
        applicantName.setText(applicant.getApplicantName());

        TextView applicantEmail = findViewById(R.id.applicantEmailDetails);
        applicantEmail.setText(applicant.getApplicantEmail());

        TextView applicantExperience = findViewById(R.id.applicantExperienceDetails);
        applicantExperience.setText(applicant.getApplicantExperience());
    }
}