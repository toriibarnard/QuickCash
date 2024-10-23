package com.example.quickcash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ApplicantAdapter extends RecyclerView.Adapter<ApplicantAdapter.ApplicantViewHolder> {

    private List<Applicant> applicantList;
    private Context context;
    private OnItemClickListener listener;

    public ApplicantAdapter(List<Applicant> applicantList, OnItemClickListener listener) {
        this.applicantList = applicantList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ApplicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicant_item_view, parent, false);
        return new ApplicantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicantViewHolder holder, int position) {
        Applicant applicant = applicantList.get(position);
        holder.applicantName.setText(applicant.getApplicantName());
        holder.applicantEmail.setText(applicant.getApplicantEmail());
        holder.applicantExperience.setText(applicant.getApplicantExperience());

        // Handle "View Applicant" button click
        holder.viewApplicantButton.setOnClickListener(v -> listener.onViewApplicantClick(applicant));
    }

    @Override
    public int getItemCount() {
        return applicantList.size();
    }

    public static class ApplicantViewHolder extends RecyclerView.ViewHolder {
        TextView applicantName;
        TextView applicantEmail;
        TextView applicantExperience;
        Button viewApplicantButton;

        public ApplicantViewHolder(@NonNull View itemView) {
            super(itemView);
            applicantName = itemView.findViewById(R.id.applicantName);
            applicantEmail = itemView.findViewById(R.id.applicantEmail);
            applicantExperience = itemView.findViewById(R.id.applicantExperience);
            viewApplicantButton = itemView.findViewById(R.id.viewApplicantButton);
        }
    }

    public interface OnItemClickListener {
        void onViewApplicantClick(Applicant applicant);
    }
}