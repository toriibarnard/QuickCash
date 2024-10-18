package com.example.quickcash;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JobApplicationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_job_application);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setUploadResumeButton();
        setSubmitButton();
    }

    protected String getName() {
        EditText name = findViewById(R.id.applicationNameBox);
        return name.getText().toString().trim();
    }

    protected String getPhone() {
        EditText phone = findViewById(R.id.applicationPhoneBox);
        return phone.getText().toString().trim();
    }

    protected String getEmail() {
        EditText email = findViewById(R.id.applicationEmailBox);
        return email.getText().toString().trim();
    }

    public void setUploadResumeButton() {
        Button uploadResume = findViewById(R.id.resumeUploadButton);
        uploadResume.setOnClickListener(view -> handelUploadResume());
    }

    public void handelUploadResume() {

    }

    public void setSubmitButton() {
        Button submitApplication = findViewById(R.id.applicationSubmitButton);
        submitApplication.setOnClickListener(view -> handelSubmitApplication());
    }

    public void handelSubmitApplication() {

    }
}