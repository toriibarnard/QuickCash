package com.example.quickcash;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
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

    private static final String STATUS_SHORTLISTED = "Shortlisted";
    private static final String STATUS_REJECTED = "Rejected";
    private static final String STATUS_PENDING = "Pending";
    private static final String STATUS_SUBMITTED = "Submitted";
    private static final String STATUS_HIRED = "Hired";

    private TextView shortlistedTextView, rejectedTextView, pendingTextView;
    private Button offerJobButton, submitButton;
    private LinearLayout inputLayout, buttonLayout;
    private EditText salaryEditText, startDateEditText;
    private DatabaseReference applicationRef;
    private Applicant applicant;
    private String applicationId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applicant_details_view);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setUpButtons();

        // Get the applicant from the intent
        applicant = (Applicant) getIntent().getSerializableExtra("applicant");
        // From the email, get the associated application node
        getApplicationNode(applicant.getApplicantEmail());
    }

    private void initializeViews() {
        shortlistedTextView = findViewById(R.id.shortlistedTextView);
        rejectedTextView = findViewById(R.id.rejectedTextView);
        pendingTextView = findViewById(R.id.pendingTextView);
        offerJobButton = findViewById(R.id.offerJobButton);
        inputLayout = findViewById(R.id.inputLayout);
        salaryEditText = findViewById(R.id.salaryEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        submitButton = findViewById(R.id.submitButton);
        buttonLayout = findViewById(R.id.buttonLayout);
    }

    private void setUpButtons() {
        Button shortlistButton = findViewById(R.id.shortlistButton);
        shortlistButton.setOnClickListener(view -> onShortlistClicked());

        Button rejectButton = findViewById(R.id.rejectButton);
        rejectButton.setOnClickListener(view -> onRejectClicked());

        Button viewResume = findViewById(R.id.viewResumeButton);
        viewResume.setOnClickListener(view -> onViewResume());

        offerJobButton.setOnClickListener(view -> onOfferJobClicked());
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
                        break;
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

            applicationRef.child("applicantStatus").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String status = snapshot.getValue(String.class);
                    manageStatusView(status);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void clearOldDetails() {
        shortlistedTextView.setVisibility(View.GONE);
        rejectedTextView.setVisibility(View.GONE);
        pendingTextView.setVisibility(View.GONE);
        offerJobButton.setVisibility(View.GONE);
        buttonLayout.setVisibility(View.GONE);
        inputLayout.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
    }

    private void manageStatusView(String status) {
        clearOldDetails();
        if (status.equals(STATUS_SHORTLISTED)) {
            buttonLayout.setVisibility(View.VISIBLE);
            findViewById(R.id.shortlistButton).setVisibility(View.GONE);
            shortlistedTextView.setVisibility(View.VISIBLE);
            offerJobButton.setVisibility(View.VISIBLE);
        } else if (status.equals(STATUS_REJECTED)) {
            rejectedTextView.setVisibility(View.VISIBLE);
        } else if (status.equals(STATUS_PENDING)) {
            buttonLayout.setVisibility(View.VISIBLE);
            findViewById(R.id.shortlistButton).setVisibility(View.GONE);
            pendingTextView.setVisibility(View.VISIBLE);
        } else if (status.equals(STATUS_SUBMITTED)) {
            buttonLayout.setVisibility(View.VISIBLE);
        } else if (status.equals(STATUS_HIRED)) {
            pendingTextView.setText("Hired!");
            pendingTextView.setVisibility(View.VISIBLE);
        }
    }

    private void onViewResume() {
        applicationRef.child("resumeUri").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String resumeUri = snapshot.getValue(String.class);

                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(resumeUri));
                startActivity(browserIntent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Resume load fail", "Error downloading resume!", error.toException());
            }
        });
    }

    private void onRejectClicked() {
        // Update application status to 'Rejected' in Firebase
        applicationRef.child("applicantStatus").setValue("Rejected")
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Application marked as Rejected", Toast.LENGTH_SHORT).show();
                    manageStatusView("Rejected");
                });

        // Update applicant object
        applicant.setApplicantStatus("Rejected");
    }

    private void onShortlistClicked() {
        applicationRef.child("applicantStatus").setValue("Shortlisted")
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(this, "Application marked as Shortlisted", Toast.LENGTH_SHORT).show();
                    manageStatusView("Shortlisted");
                });

        applicant.setApplicantStatus("Shortlisted");
    }

    private void onOfferJobClicked() {
        buttonLayout.setVisibility(View.GONE);
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
        String salary = salaryEditText.getText().toString().trim();
        String startDate = startDateEditText.getText().toString().trim();

        if (isValidSalary(salary) && isValidDate(startDate)) {
            HashMap<String, Object> updates = new HashMap<>();
            updates.put("salary", salary);
            updates.put("startDate", startDate);
            updates.put("applicantStatus", "Pending");

            applicationRef.updateChildren(updates)
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Job offer sent. Application marked as Pending", Toast.LENGTH_SHORT).show();
                        manageStatusView("Pending");
                    });
        }
    }

    private boolean isValidSalary(String salary) {
        if (salary.isEmpty()) {
            salaryEditText.setError("Salary required");
            salaryEditText.requestFocus();
            return false;
        }
        return true;
    }

    private boolean isValidDate(String startDate) {
        if (startDate.isEmpty()) {
            startDateEditText.setError("Start date required");
            startDateEditText.requestFocus();
            return false;
        }
        return true;
    }
}
