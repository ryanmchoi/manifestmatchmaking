package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.myapplication.models.Aircraft;
import com.example.myapplication.models.Manifest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class DacoViewAircraftDetails extends AppCompatActivity {
    String aircraftName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daco_view_aircraft_details);

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        String manifestClicked = getIntent().getStringExtra("Listviewclickvalue");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");
        DatabaseReference aDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Aircrafts");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //get the assigned aircraft name of the manifest clicked so that you can use it in the aircrafts table
                aircraftName = dataSnapshot.child(manifestClicked).child("aircraft_name").getValue(String.class);

                aDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //get the values from the aircrafts table based on the aircraft name
                        Integer departureTime = snapshot.child(aircraftName).child("departure_time").getValue(Integer.class);
                        String location = snapshot.child(aircraftName).child("location").getValue(String.class);
                        String status = snapshot.child(aircraftName).child("status").getValue(String.class);
                        Integer maxCapacity = snapshot.child(aircraftName).child("max_capacity").getValue(Integer.class);

                        //bring in all the textViews from the activity_view_manifest xml file
                        TextView aircraftNameTextView = findViewById(R.id.aircraft_name_label1);
                        TextView locationTextView = findViewById(R.id.aircraft_location);
                        TextView departureTimeTextView = findViewById(R.id.aircraft_departure_time);
                        TextView statusTextView = findViewById(R.id.aircraft_status);
                        TextView maxCapacityTextView = findViewById(R.id.aircraft_max_capacity);

                        //Replace the existing text of textView with that of the information retrieved from database
                        String aircraftText = "Aircraft: " + aircraftName;
                        String locationText = "Location: " + location;
                        String departText = "Departure Time: " + departureTime;
                        String statusText = "Status: " + status;
                        String maxCapacityText = "Max Capacity: " + maxCapacity;

                        //set the text of the textViews
                        aircraftNameTextView.setText(aircraftText);
                        locationTextView.setText(locationText);
                        departureTimeTextView.setText(departText);
                        statusTextView.setText(statusText);
                        maxCapacityTextView.setText(maxCapacityText);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("Aircraft Error Tag", "loadAircraft:onCancelled", error.toException());
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}