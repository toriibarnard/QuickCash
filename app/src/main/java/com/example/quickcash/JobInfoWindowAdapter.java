package com.example.quickcash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class JobInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    // Instance variables
    private View view;

    // Constructor
    public JobInfoWindowAdapter(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.maps_marker_window, null);
    }

    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    // Set up the info window with the title and the snippet and return the view
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        String title = marker.getTitle();
        String snippet = marker.getSnippet();
        setTitle(title);
        setSnippet(snippet);

        return view;
    }

    // Set the title for the window
    public void setTitle(String title) {
        TextView titleTextView = view.findViewById(R.id.markerJobTitle);
        titleTextView.setText(title);
    }

    // Set the snippet for the window
    public void setSnippet(String snippet) {
        TextView jobInfoTextView = view.findViewById(R.id.markerJobSnippet);
        jobInfoTextView.setText(snippet);
    }
}
