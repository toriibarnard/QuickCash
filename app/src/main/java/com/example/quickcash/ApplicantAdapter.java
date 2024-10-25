package com.example.quickcash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.ApplicantViewHolder> {

    private List<Applicant> applicantList;  // list of applicants
    private OnItemClickListener listener;   // for handling item clicks

    // constructor for the adapter taking the list of applicants and the click listener
    public ApplicantAdapter(List<Applicant> applicantList, OnItemClickListener listener) {
        this.applicantList = applicantList;
        this.listener = listener;
    }

    // method to create a new ViewHolder for each item in the RecyclerView
    @NonNull
    @Override
    public ApplicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the layout for each applicant item (applicant_item_view)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_item_view, parent, false);
        return new ApplicantViewHolder(view);  // return a new ViewHolder instance
    }

    // method to bind applicant details to the ViewHolder for each item
    @Override
    public void onBindViewHolder(@NonNull ApplicantViewHolder holder, int position) {
        // get the current applicant based on the position in the list
        Applicant applicant = applicantList.get(position);

        // set the applicants details in the TextView
        holder.applicantName.setText(applicant.getApplicantName());
        holder.applicantEmail.setText(applicant.getApplicantEmail());
        holder.applicantPhone.setText(applicant.getApplicantPhone());

        // set an OnClickListener for the View Applicant button
        // when clicked, it triggers the listeners onViewApplicantClick method
        holder.viewApplicantButton.setOnClickListener(v -> listener.onViewApplicantClick(applicant));
    }

    // method to return the total number of applicants in the list
    @Override
    public int getItemCount() {
        return applicantList.size();
    }

    // ViewHolder class to hold references to the views for each applicant item
    public static class ApplicantViewHolder extends RecyclerView.ViewHolder {
        // views in the applicant_item_view layout
        TextView applicantName;
        TextView applicantEmail;
        TextView applicantPhone;
        Button viewApplicantButton;

        // ViewHolder constructor
        public ApplicantViewHolder(@NonNull View itemView) {
            super(itemView);
            // find and store references to the views
            applicantName = itemView.findViewById(R.id.applicantName);
            applicantEmail = itemView.findViewById(R.id.applicantEmail);
            applicantPhone = itemView.findViewById(R.id.applicantPhone);
            viewApplicantButton = itemView.findViewById(R.id.viewApplicantButton);
        }
    }

    // handling click events on applicant items
    public interface OnItemClickListener {
        void onViewApplicantClick(Applicant applicant);
    }
}