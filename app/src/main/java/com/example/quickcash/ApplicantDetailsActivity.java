package com.example.quickcash;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ApplicantDetailsActivity extends AppCompatActivity {

    private TextView rejectedTextView;
    private LinearLayout inputLayout;
    private EditText salaryEditText;
    private EditText startDateEditText;
    private Button submitButton;
    private LinearLayout buttonLayout;
    private DatabaseReference applicationRef;
    private Applicant applicant;
    private String applicationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applicant_details_view);

        rejectedTextView = findViewById(R.id.rejectedTextView);
        inputLayout = findViewById(R.id.inputLayout);
        salaryEditText = findViewById(R.id.salaryEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        submitButton = findViewById(R.id.submitButton);
        buttonLayout = findViewById(R.id.buttonLayout);
        Button shortlistButton = findViewById(R.id.shortlistButton);
        Button rejectButton = findViewById(R.id.rejectButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the applicant from the intent
        applicant = (Applicant) getIntent().getSerializableExtra("applicant");

        // From the email, get the associated application node
        getApplicationNode(applicant.getApplicantEmail());

        // Set up click listeners
        rejectButton.setOnClickListener(view -> onRejectClicked());
        shortlistButton.setOnClickListener(view -> onShortlistClicked());
        startDateEditText.setOnClickListener(view -> showDatePicker());
        submitButton.setOnClickListener(view -> onSubmitClicked());
    }

    private void getApplicationNode(String applicantEmail) {
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("applications");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot applicationSnapshot : snapshot.getChildren()) {
                    // Retrieve the applicantEmail field within each application node
                    String email = applicationSnapshot.child("applicantEmail").getValue(String.class);

                    // Check if this email matches the input email
                    if (applicantEmail.equals(email)) {
                        applicationId = applicationSnapshot.getKey();  // Set the instance variable
                        applicationRef = FirebaseDatabase.getInstance().getReference("applications").child(applicationId);
                        // Proceed with further setup after `applicationId` is retrieved
                        setupApplicantDetails();
                        break; // Stop searching once we find the correct application
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.err.println("Error retrieving application: " + error.getMessage());
            }
        });
    }

    private void setupApplicantDetails() {
        if (applicant != null) {
            // Set applicant details in the UI
            TextView applicantNameTextView = findViewById(R.id.applicantNameTextView);
            TextView applicantEmailTextView = findViewById(R.id.applicantEmailTextView);
            TextView applicantPhoneTextView = findViewById(R.id.applicantPhoneTextView);
            TextView jobIdTextView = findViewById(R.id.applicantJobIDTextView);

            applicantNameTextView.setText("Name: "+applicant.getApplicantName());
            applicantEmailTextView.setText("Email: "+applicant.getApplicantEmail());
            applicantPhoneTextView.setText("Phone: "+applicant.getApplicantPhone());
            jobIdTextView.setText("Job ID: "+applicant.getjobId());
        }
    }

    private void onRejectClicked() {
        // Hide the buttons
        buttonLayout.setVisibility(View.GONE);

        // Show the 'Rejected' TextView
        rejectedTextView.setVisibility(View.VISIBLE);

        // Update application status to 'Rejected' in Firebase
        applicationRef.child("applicantStatus").setValue("Rejected");

        // Update applicant object
        applicant.setApplicantStatus("Rejected");
    }

    private void onShortlistClicked() {
        // Hide the buttons
        buttonLayout.setVisibility(View.GONE);

        // Show the input fields and submit button
        inputLayout.setVisibility(View.VISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    private void showDatePicker() {
        // Show a DatePickerDialog and set the selected date to startDateEditText
        Calendar calendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(ApplicantDetailsActivity.this,
                (view, year, month, day) -> {
                    month = month + 1;
                    String date = day + "/" + month + "/" + year;
                    startDateEditText.setText(date);
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void onSubmitClicked() {
        String salaryStr = salaryEditText.getText().toString().trim();
        String startDate = startDateEditText.getText().toString().trim();

        if (salaryStr.isEmpty()) {
            salaryEditText.setError("Salary required");
            salaryEditText.requestFocus();
            return;
        }

        if (startDate.isEmpty()) {
            startDateEditText.setError("Start date required");
            startDateEditText.requestFocus();
            return;
        }

        double salary = Double.parseDouble(salaryStr);

        // Push salary and start date to Firebase
        applicationRef.child("salary").setValue(salary);
        applicationRef.child("startDate").setValue(startDate);

        // Update application status to 'Shortlisted' in Firebase
        applicationRef.child("applicantStatus").setValue("Shortlisted");


        // Update applicant object
        applicant.setApplicantStatus("Shortlisted");

        // Show a confirmation message
        Toast.makeText(this, "Application updated", Toast.LENGTH_SHORT).show();

        // Hide the input fields and submit button
        inputLayout.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
    }
}
