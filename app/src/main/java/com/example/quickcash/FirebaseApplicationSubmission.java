package com.example.quickcash;

import android.content.Context;
import android.net.Uri;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.UUID;

public class FirebaseApplicationSubmission {
    private DatabaseReference dbRef;
    private StorageReference stRef;
    private Context context;
    private String appName;
    private String appPhone;
    private String appEmail;
    private String jobId;
    private Uri fileUri;
    private String resumeUri;

    public FirebaseApplicationSubmission(String appName, String appPhone, String appEmail, String jobId, Uri fileUri, Context context) {
        this.appName = appName;
        this.appPhone = appPhone;
        this.appEmail = appEmail;
        this.jobId = jobId;
        this.fileUri = fileUri;
        this.context = context;
        this.dbRef = FirebaseDatabase.getInstance().getReference("applications");
        this.stRef = FirebaseStorage.getInstance().getReference();
    }

    public void submit2Firebase() {
        StorageReference resumeRef = stRef.child("resume/"+appEmail+"_"+jobId+".pdf");
        resumeRef.putFile(fileUri)
                .addOnSuccessListener(upload -> {
                    resumeRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                        this.resumeUri = downloadUri.toString();

                        String applicationId = UUID.randomUUID().toString();
                        HashMap<String, String> applicationData = new HashMap<>();
                        applicationData.put("jobId", jobId);
                        applicationData.put("applicantEmail", appEmail);
                        applicationData.put("resumeUri", resumeUri);
                        applicationData.put("applicantName", appName);
                        applicationData.put("applicantPhone", appPhone);
                        applicationData.put("applicantStatus", "Submitted");

                        dbRef.child(applicationId).setValue(applicationData);
                    });
                })
                .addOnFailureListener(e -> Toast.makeText(context, "File Uploaded!", Toast.LENGTH_SHORT).show());
    }
}
