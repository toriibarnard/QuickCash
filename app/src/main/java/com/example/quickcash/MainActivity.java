package com.example.quickcash;

import android.os.Bundle;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity {

    TextView textView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String FIREBASE_DATABASE = "https://quick-cash-64e58-default-rtdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.textView);
        connectToDB();
        writeToDB();
        readFromDB();
    }

    private void connectToDB() {
        database = FirebaseDatabase.getInstance(FIREBASE_DATABASE);
        databaseReference = database.getReference("message");
    }

    private void writeToDB() {
        databaseReference.setValue("Hello, Quick Cash!");
    }

    private void readFromDB() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                final String message = snapshot.getValue(String.class);
                textView.setText(message);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                final String errorValue = error.getMessage();
                textView.setText(errorValue);
            }
        });
    }
}