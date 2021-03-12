package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewAircraftDetails extends AppCompatActivity {
    String aircraftName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aircraft_details);

        String manifestClicked = getIntent().getStringExtra("Listviewclickvalue");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get values from database for the manifest that was clicked
                aircraftName = dataSnapshot.child(manifestClicked).child("aircraft_name").getValue(String.class);
                Integer departureTime = dataSnapshot.child(manifestClicked).child("departure_time").getValue(Integer.class);
                String location = dataSnapshot.child(manifestClicked).child("location").getValue(String.class);

                //bring in all the textViews from the activity_view_manifest xml file
                TextView aircraftNameTextView = findViewById(R.id.aircraft_label);
                TextView locationTextView = findViewById(R.id.aircraft_location_label1);
                TextView departureTimeTextView = findViewById(R.id.departure_time_label1);

                //Replace the existing text of textView with that of the information retrieved from database
                String aircraftText = "Aircraft: " + aircraftName;
                String locationText = "Location: " + location;
                String departText = "Departure Time: " + departureTime;

                //set the text of the textViews
                aircraftNameTextView.setText(aircraftText);
                locationTextView.setText(locationText);
                departureTimeTextView.setText(departText);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });
    }
}
