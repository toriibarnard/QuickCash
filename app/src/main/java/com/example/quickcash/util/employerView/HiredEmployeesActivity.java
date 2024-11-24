package com.example.quickcash.util.employerView;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.R;
import com.example.quickcash.firebase.FirebaseHiredEmployees;
import com.example.quickcash.util.employeeView.ApplicationAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HiredEmployeesActivity extends AppCompatActivity implements HiredEmployeeAdapter.OnItemClickListener {

    String employerId;
    FirebaseHiredEmployees firebaseHiredEmployees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hired_employees);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();
        setUpRecyclerView();
    }

    private void initialize() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            employerId = currentUser.getEmail();
        }
        firebaseHiredEmployees = new FirebaseHiredEmployees();
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.hiredEmployeesRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Fetch data asynchronously and set up RecyclerView once data is available
        firebaseHiredEmployees.returnHiredEmployees(employerId, employees -> {
            HiredEmployeeAdapter adapter = new HiredEmployeeAdapter(employees, this);
            recyclerView.setAdapter(adapter);
        });
    }

    @Override
    public void onMarkCompleteClick(HiredEmployee employee) {

    }
}