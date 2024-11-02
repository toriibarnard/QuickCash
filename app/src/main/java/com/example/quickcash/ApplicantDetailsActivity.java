package com.example.quickcash;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private Button hireButton;
    private Applicant applicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_applicant_details_view);

        // Initialize views
        TextView applicantNameTextView = findViewById(R.id.applicantNameTextView);
        TextView applicantEmailTextView = findViewById(R.id.applicantEmailTextView);
        TextView applicantPhoneTextView = findViewById(R.id.applicantPhoneTextView);
        TextView applicantJobIDTextView = findViewById(R.id.applicantJobIDTextView);
        rejectedTextView = findViewById(R.id.rejectedTextView);
        inputLayout = findViewById(R.id.inputLayout);
        salaryEditText = findViewById(R.id.salaryEditText);
        startDateEditText = findViewById(R.id.startDateEditText);
        hireButton = findViewById(R.id.hireButton);
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

        // Initialize Firebase references
        String applicationId = applicant.getApplicantID();
        applicationRef = FirebaseDatabase.getInstance().getReference("applications")
                .child(applicant.getApplicantID());
        applicantStatusesRef = FirebaseDatabase.getInstance().getReference("applicantStatuses");

        // Set applicant details in the UI
        applicantNameTextView.setText(applicant.getApplicantName());
        applicantEmailTextView.setText(applicant.getApplicantEmail());
        applicantPhoneTextView.setText(applicant.getApplicantPhone());
        applicantJobIDTextView.setText(applicant.getApplicantJobID());

        // Set up click listeners
        rejectButton.setOnClickListener(view -> onRejectClicked());
        shortlistButton.setOnClickListener(view -> onShortlistClicked());
        startDateEditText.setOnClickListener(view -> showDatePicker());
        hireButton.setOnClickListener(view -> onHireClicked());
    }

    private void onRejectClicked() {
        // Hide the buttons
        buttonLayout.setVisibility(View.GONE);

        // Show the 'Rejected' TextView
        rejectedTextView.setVisibility(View.VISIBLE);

        // Update application status to 'Rejected' in Firebase
        applicationRef.child("employerStatus").setValue("Rejected");

        // Update applicantStatuses node
        Map<String, Object> statusUpdate = new HashMap<>();
        statusUpdate.put("name", applicant.getApplicantName());
        statusUpdate.put("status", "Rejected");

        applicantStatusesRef.child(applicant.getApplicantID()).updateChildren(statusUpdate);

        // Update applicant object
        applicant.setEmployerStatus("Rejected");
    }

    private void onShortlistClicked() {
        // Hide the buttons
        buttonLayout.setVisibility(View.GONE);

        // Show the input fields and submit button
        inputLayout.setVisibility(View.VISIBLE);
        hireButton.setVisibility(View.VISIBLE);

        applicationRef.child("employerStatus").setValue("Shortlisted");

        // Update applicantStatuses node
        Map<String, Object> statusUpdate = new HashMap<>();
        statusUpdate.put("name", applicant.getApplicantName());
        statusUpdate.put("status", "Shortlisted");

        applicantStatusesRef.child(applicant.getApplicantID()).updateChildren(statusUpdate);

        // Update applicant object
        applicant.setEmployerStatus("Shortlisted");
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

    private void onHireClicked() {
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

        // Update application status to 'Pending' in Firebase
        applicationRef.child("employerStatus").setValue("Pending");

        // Update applicantStatuses node
        Map<String, Object> statusUpdate = new HashMap<>();
        statusUpdate.put("name", applicant.getApplicantName());
        statusUpdate.put("status", "Pending");

        applicantStatusesRef.child(applicant.getApplicantID()).updateChildren(statusUpdate);

        // Update applicant object
        applicant.setEmployerStatus("Pending");

        // Show a confirmation message
        Toast.makeText(this, "Application updated to Pending", Toast.LENGTH_SHORT).show();

        // Hide the input fields and hire button
        inputLayout.setVisibility(View.GONE);
        hireButton.setVisibility(View.GONE);
    }
}
