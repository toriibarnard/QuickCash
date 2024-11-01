package com.example.quickcash;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
    private DatabaseReference applicantStatusesRef;
    private Applicant applicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applicant_details_view);

        Button shortlistButton = findViewById(R.id.shortlistButton);
        Button rejectButton = findViewById(R.id.rejectButton);
        rejectedTextView = findViewById(R.id.rejectedTextView);
        inputLayout = findViewById(R.id.inputLayout);
        salaryEditText = findViewById(R.id.salaryEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        submitButton = findViewById(R.id.submitButton);
        buttonLayout = findViewById(R.id.buttonLayout);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Get the applicant from the intent.
        applicant = getIntent().getSerializableExtra("applicant", Applicant.class);

        // Initialize Firebase references using applicant's user ID
        String applicantUserId = applicant.getUserId(); // Use getUserId() method
        String applicationId = applicant.getApplicantJobID(); // Assuming applicantJobID is the applicationId

        applicationRef = FirebaseDatabase.getInstance().getReference("applications")
                .child(applicantUserId)
                .child(applicationId);
        applicantStatusesRef = FirebaseDatabase.getInstance().getReference("applicantStatuses");

        TextView applicantJobID = findViewById(R.id.applicantJobIDDetails);
        applicantJobID.setText(applicant.getApplicantJobID());

        TextView applicantName = findViewById(R.id.applicantDetailsTitle);
        applicantName.setText(applicant.getApplicantName());

        TextView applicantEmail = findViewById(R.id.applicantEmailDetails);
        applicantEmail.setText(applicant.getApplicantEmail());

        TextView applicantExperience = findViewById(R.id.applicantExperienceDetails);
        applicantExperience.setText(applicant.getApplicantExperience());

        rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRejectClicked();
            }
        });

        shortlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShortlistClicked();
            }
        });

        startDateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitClicked();
            }
        });
    }

    private void onRejectClicked() {
        // Hide the buttons
        buttonLayout.setVisibility(View.GONE);

        // Show the 'Rejected' TextView
        rejectedTextView.setVisibility(View.VISIBLE);

        // Update application status to 'Rejected' in Firebase
        applicationRef.child("status").setValue("Rejected");

        // Update applicantStatuses node
        Map<String, Object> statusUpdate = new HashMap<>();
        statusUpdate.put("name", applicant.getApplicantName());
        statusUpdate.put("status", "Rejected");

        applicantStatusesRef.child(applicant.getUserId()).updateChildren(statusUpdate);
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
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
                        startDateEditText.setText(date);
                    }
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
        applicationRef.child("status").setValue("Shortlisted");

        // Update applicantStatuses node
        Map<String, Object> statusUpdate = new HashMap<>();
        statusUpdate.put("name", applicant.getApplicantName());
        statusUpdate.put("status", "Shortlisted");

        applicantStatusesRef.child(applicant.getUserId()).updateChildren(statusUpdate);

        // Show a confirmation message
        Toast.makeText(this, "Application updated", Toast.LENGTH_SHORT).show();

        // Hide the input fields and submit button
        inputLayout.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
    }
}
