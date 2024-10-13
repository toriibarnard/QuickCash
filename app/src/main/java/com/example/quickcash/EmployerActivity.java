package com.example.quickcash;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EmployerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_employer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setCreatePostingButton();
        setViewDetailsButton();
    }

    public void setCreatePostingButton() {
        ImageButton createPost = findViewById(R.id.createPostButton);
        createPost.setOnClickListener(view -> makeNewPost());
    }

    public void makeNewPost() {
        Intent newPost = new Intent(EmployerActivity.this, JobPostingActivity.class);
        EmployerActivity.this.startActivity(newPost);
    }

    public void setViewDetailsButton() {
        Button viewDetails = findViewById(R.id.viewDetailsButton);
        viewDetails.setOnClickListener(view -> viewJobDetails());
    }

    public void viewJobDetails() {
        Intent details = new Intent(EmployerActivity.this, JobDetailsActivity.class);
        EmployerActivity.this.startActivity(details);
    }
}