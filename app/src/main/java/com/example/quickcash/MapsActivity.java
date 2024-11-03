package com.example.quickcash;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.quickcash.databinding.ActivityMapsBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private DatabaseReference jobRef;
    private HashMap<String, Marker> existingMarkers = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        jobRef = FirebaseDatabase.getInstance().getReference("job_posts");
    }

    private LatLng getLatLng(String location) {
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addresses = geocoder.getFromLocationName(location, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address address = addresses.get(0);
                return new LatLng(address.getLatitude(), address.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setInfoWindowAdapter(new JobInfoWindowAdapter(this));

        jobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot jobSnapshot : snapshot.getChildren()) {
                    String jobId = jobSnapshot.getKey();
                    String jobTitle = jobSnapshot.child("jobTitle").getValue(String.class);
                    String companyName = jobSnapshot.child("companyName").getValue(String.class);
                    String jobType = jobSnapshot.child("jobType").getValue(String.class);
                    String location = jobSnapshot.child("location").getValue(String.class);

                    LatLng locationLatLng = getLatLng(location);

                    if (!existingMarkers.containsKey(jobId)) {
                        String jobSnippet = getSnippet(companyName, jobType);
                        Marker marker = mMap.addMarker(new MarkerOptions()
                                .position(locationLatLng).title(jobTitle).snippet(jobSnippet));
                        existingMarkers.put(jobId, marker);
                    }
                }
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(existingMarkers.values().iterator().next().getPosition(), 5));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MapsActivity.this, "Failed to load job postings", Toast.LENGTH_SHORT).show();
            }
        });

        mMap.setOnMarkerClickListener(marker -> {
            marker.showInfoWindow();
            return true;
        });
    }

    public String getSnippet(String companyName, String jobType) {
        return "Company: "+companyName+"\nType: "+jobType;
    }

    public GoogleMap getMap() {
        return this.mMap;
    }
}