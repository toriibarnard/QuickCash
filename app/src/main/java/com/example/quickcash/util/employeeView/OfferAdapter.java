package com.example.quickcash.util.employeeView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.util.employerView.Applicant;
import com.example.quickcash.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.OfferViewHolder> {

    private List<Applicant> offerList;
    private OnOfferClickListener listener;

    public OfferAdapter(List<Applicant> offerList, OnOfferClickListener listener) {
        this.offerList = offerList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_item_view, parent, false);
        return new OfferViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        Applicant offer = offerList.get(position);

        // set salary and start date from the Applicant object
        holder.salaryTextView.setText("Salary: " + offer.getSalary());
        holder.startDateTextView.setText("Start Date: " + offer.getStartDate());

        // fetch job title and company name using jobId from the database
        fetchJobDetails(offer.getJobId(), holder);

        // set click listeners for accept and reject buttons
        holder.acceptButton.setOnClickListener(v -> listener.onAcceptClick(offer));
        holder.rejectButton.setOnClickListener(v -> listener.onRejectClick(offer));
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    public static class OfferViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitleTextView, companyNameTextView, salaryTextView, startDateTextView;
        Button acceptButton, rejectButton;

        public OfferViewHolder(@NonNull View itemView) {
            super(itemView);
            jobTitleTextView = itemView.findViewById(R.id.jobTitleTextView);
            companyNameTextView = itemView.findViewById(R.id.companyNameTextView);
            salaryTextView = itemView.findViewById(R.id.salaryTextView);
            startDateTextView = itemView.findViewById(R.id.startDateTextView);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            rejectButton = itemView.findViewById(R.id.rejectButton);
        }
    }

    // Interface for handling click events on offers
    public interface OnOfferClickListener {
        void onAcceptClick(Applicant offer);
        void onRejectClick(Applicant offer);
    }

    // Method to fetch job details using jobId
    private void fetchJobDetails(String jobId, OfferViewHolder holder) {
        DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference("job_posts").child(jobId);

        jobRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    // get job title and company name
                    String jobTitle = snapshot.child("jobTitle").getValue(String.class);
                    String companyName = snapshot.child("companyName").getValue(String.class);

                    // set the job title and company name to the ViewHolder
                    holder.jobTitleTextView.setText(jobTitle != null ? jobTitle : "N/A");
                    holder.companyNameTextView.setText(companyName != null ? companyName : "N/A");
                } else {
                    holder.jobTitleTextView.setText("Unknown Job");
                    holder.companyNameTextView.setText("Unknown Company");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                holder.jobTitleTextView.setText("Error loading job");
                holder.companyNameTextView.setText("Error loading company");
            }
        });
    }
}