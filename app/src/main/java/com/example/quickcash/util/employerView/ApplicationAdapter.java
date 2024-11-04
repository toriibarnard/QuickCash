package com.example.quickcash.util.employerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quickcash.util.employeeView.ApplicationData;
import com.example.quickcash.R;

import java.util.ArrayList;

public class ApplicationAdapter extends RecyclerView.Adapter<ApplicationAdapter.ApplicationViewHolder> {

    private ArrayList<ApplicationData> applications;

    public ApplicationAdapter(ArrayList<ApplicationData> applications) {
        this.applications = applications;
    }

    @NonNull
    @Override
    public ApplicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_application_item_view, parent, false);
        return new ApplicationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApplicationViewHolder holder, int position) {
        ApplicationData application = applications.get(position);
        Log.d("ApplicationAdapter", "Binding application at position: " + position);
        holder.applicationJobID.setText(application.getJobID());
        holder.applicationStatus.setText(application.getApplicationStatus());
        // Add more fields if needed
    }

    @Override
    public int getItemCount() {
        return applications.size();
    }

    public static class ApplicationViewHolder extends RecyclerView.ViewHolder {
        TextView applicationJobID;
        TextView applicationStatus;

        public ApplicationViewHolder(@NonNull View itemView) {
            super(itemView);
            applicationJobID = itemView.findViewById(R.id.applicationJobID);
            applicationStatus = itemView.findViewById(R.id.applicationStatus);
        }
    }
}
