package com.example.quickcash;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * FirebaseCRUD class abstracts Firebase Realtime Database operations for JobPost objects.
 */
public class FirebaseCRUD {

    public static final String FIREBASE_DATABASE_URL = "https://quick-cash-64e58-default-rtdb.firebaseio.com/";

    private final DatabaseReference databaseReference;
    private final Map<String, JobPost> cachedJobPosts;

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    /**
     * Constructor initializes the database reference and the cache.
     * It sets up a listener to keep the cache updated with any changes in the database.
     *
     * @param firebaseDatabase the FirebaseDatabase instance.
     */
    public FirebaseCRUD(FirebaseDatabase firebaseDatabase) {
        this.databaseReference = firebaseDatabase.getReference("job_posts");
        this.cachedJobPosts = new HashMap<>();
        initializeCache();
    }

    /**
     * Initializes the cache by setting up a listener on the database reference.
     * The cache is updated whenever data changes in the "job_posts" node.
     */
    private void initializeCache() {

        // Set up a listener to update cachedJobPosts whenever data changes.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                cachedJobPosts.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    JobPost jobPost = postSnapshot.getValue(JobPost.class);
                    if (jobPost != null) {
                        cachedJobPosts.put(jobPost.getJobID(), jobPost);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                // Handle possible errors, e.g., log the error.
                System.err.println("Error updating cache: " + error.getMessage());
            }
        });
    }

    /**
     * Creates a new job post in the Firebase database.
     * Assumes the jobPost argument has a unique jobID.
     *
     * @param jobPost the JobPost object to create.
     */
    public void createJobPost(JobPost jobPost) {
        String jobID = jobPost.getJobID();
        databaseReference.child(jobID).setValue(jobPost);
    }

    /**
     * Retrieves the JobPost from the cache with the given jobID, if found.
     * Otherwise, returns null.
     *
     * @param jobID the job ID to search for.
     * @return the JobPost object if found, or null if not found.
     */
    public JobPost readJobPost(String jobID) {
        return cachedJobPosts.get(jobID);
    }

    /**
     * Retrieves all job posts from the cache.
     *
     * @return an ArrayList of all JobPost objects.
     */
    public ArrayList<JobPost> readAllJobPosts() {
        return new ArrayList<>(cachedJobPosts.values());
    }

    /**
     * Retrieves all job posts from the cache that match the given jobPosterID.
     *
     * @param jobPosterID the ID of the job poster (employer email).
     * @return an ArrayList of JobPost objects.
     */
    public ArrayList<JobPost> readJobPostsByPosterID(String jobPosterID) {
        ArrayList<JobPost> jobPostsByPoster = new ArrayList<>();
        for (JobPost jobPost : cachedJobPosts.values()) {
            if (jobPosterID.equals(jobPost.getJobPosterID())) {
                jobPostsByPoster.add(jobPost);
            }
        }
        return jobPostsByPoster;
    }

    /**
     * Updates an existing job post in the database.
     * If the jobID exists, updates all fields from the updatedJobPost argument except the jobID.
     *
     * @param jobID          the job ID of the post to update.
     * @param updatedJobPost the JobPost object containing updated data.
     */
    public void updateJobPost(String jobID, JobPost updatedJobPost) {

        // Ensure the jobID remains the same.
        updatedJobPost.setJobID(jobID);

        // Check if jobID exists in the cache.
        if (cachedJobPosts.containsKey(jobID)) {
            databaseReference.child(jobID).setValue(updatedJobPost);
        } else {

            // Handle the case where jobID does not exist.
            throw new IllegalArgumentException("JobID " + jobID + " does not exist. Cannot update.");
        }
    }

    /**
     * Removes a job post from the Firebase database.
     *
     * @param jobID the job ID of the post to delete.
     */
    public void deleteJobPost(String jobID) {
        databaseReference.child(jobID).removeValue();
    }
}
