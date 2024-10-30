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

    // Instance variables
    private DatabaseReference dbRef;
    private StorageReference stRef;
    private Context context;
    private String appName;
    private String appPhone;
    private String appEmail;
    private String jobId;
    private Uri fileUri;
    private String resumeUri;

    // Constructor
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

    /* This method is used to upload the resume to Firebase storage and push application details to
     * Firebase database.
     * This will also extract the resume download link and attach it to the application
     */
    public void submit2Firebase() {
        // Path and name of the resume file in Firebase storage
        StorageReference resumeRef = stRef.child("resume/"+appEmail+"_"+jobId+".pdf");

        // Upload the file to Firebase Storage
        resumeRef.putFile(fileUri)
                .addOnSuccessListener(upload -> {
                    // After uploading, get the download Url for the uploaded resume file
                    resumeRef.getDownloadUrl().addOnSuccessListener(downloadUri -> {
                        // Convert the download Uri to string to store it in the database with the application
                        this.resumeUri = downloadUri.toString();

                        // Get the application data to be pushed
                        HashMap<String, String> applicationData = getApplicationData();
                        // Generate a unique application ID to keep track of this application
                        String applicationId = UUID.randomUUID().toString();
                        // Push all the information to database
                        dbRef.child(applicationId).setValue(applicationData);
                    });
                })
                .addOnFailureListener(e -> Toast.makeText(context, "File Uploaded!", Toast.LENGTH_SHORT).show());
    }

    // This method gathers all the information about the application to store it in database
    private HashMap<String, String> getApplicationData() {
        HashMap<String, String> applicationData = new HashMap<>();
        applicationData.put("jobId", jobId);
        applicationData.put("applicantEmail", appEmail);
        applicationData.put("resumeUri", resumeUri);
        applicationData.put("applicantName", appName);
        applicationData.put("applicantPhone", appPhone);
        applicationData.put("applicantStatus", "Submitted");
        return applicationData;
    }
}
