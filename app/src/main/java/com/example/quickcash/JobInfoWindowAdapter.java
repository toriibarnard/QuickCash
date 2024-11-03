package com.example.quickcash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class JobInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private View view;

    public JobInfoWindowAdapter(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.maps_marker_window, null);
    }

    @Override
    public View getInfoContents(@NonNull Marker marker) {
        return null;
    }

    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        String title = marker.getTitle();
        String snippet = marker.getSnippet();

        TextView titleTextView = view.findViewById(R.id.markerJobTitle);
        titleTextView.setText(title);

        TextView jobInfoTextView = view.findViewById(R.id.markerJobSnippet);
        jobInfoTextView.setText(snippet);

        return view;
    }
}
