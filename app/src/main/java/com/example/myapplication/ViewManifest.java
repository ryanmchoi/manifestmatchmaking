package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewManifest extends AppCompatActivity {

    private Button viewRangerBtn;
    Button changeAircraftButton;
    Button addRangerButton;
    String aircraftName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manifest);

        String manifestClicked = getIntent().getStringExtra("Listviewclickvalue");

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //get values from database for the manifest that was clicked
                aircraftName = dataSnapshot.child(manifestClicked).child("aircraft_name").getValue(String.class);
                Integer departureTime = dataSnapshot.child(manifestClicked).child("departure_time").getValue(Integer.class);
                String location = dataSnapshot.child(manifestClicked).child("location").getValue(String.class);
                Integer maxCapacity = dataSnapshot.child(manifestClicked).child("max_capacity").getValue(Integer.class);
                String status = dataSnapshot.child(manifestClicked).child("status").getValue(String.class);

                //bring in all the textViews from the activity_view_manifest xml file
                TextView manifestNameTextView = findViewById(R.id.manifest_label);
                TextView aircraftNameTextView = findViewById(R.id.aircraft_name_label);
                TextView locationTextView = findViewById(R.id.aircraft_location_label);
                TextView departureTimeTextView = findViewById(R.id.departure_time_label);
                TextView capacityTextView = findViewById(R.id.max_capacity_label);
                TextView statusTextView = findViewById(R.id.status_label);

                //Replace the existing text of textView with that of the information retrieved from database
                String manifestText = "Manifest " + manifestClicked;
                String aircraftText = "Aircraft: " + aircraftName;
                String locationText = "Location: " + location;
                String departText = "Departure Time: " + departureTime;
                String capacityText = "Max Capacity: " + maxCapacity;
                String statusText = "Status: " + status;

                //set the text of the textViews
                manifestNameTextView.setText(manifestText);
                aircraftNameTextView.setText(aircraftText);
                locationTextView.setText(locationText);
                departureTimeTextView.setText(departText);
                capacityTextView.setText(capacityText);
                statusTextView.setText(statusText);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });

        viewRangerBtn = findViewById(R.id.viewPersonnelButton);

        viewRangerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewRangerIntent = new Intent(ViewManifest.this, RangerList.class);
                viewRangerIntent.putExtra("Listviewclickvalue", manifestClicked);
                startActivity(viewRangerIntent);
            }
        });

        changeAircraftButton = findViewById(R.id.changeAircraftButton);
        changeAircraftButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent changeAircraftIntent = new Intent(ViewManifest.this, ChangeAircraft.class);
                changeAircraftIntent.putExtra("Listviewclickvalue", manifestClicked);
                changeAircraftIntent.putExtra("aircraftName", aircraftName);
                startActivity(changeAircraftIntent);
            }
        });

        addRangerButton = findViewById(R.id.addRangerButton);
        addRangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createRangerIntent = new Intent(ViewManifest.this, CreateRanger.class);
                createRangerIntent.putExtra("Listviewclickvalue", manifestClicked);
                startActivity(createRangerIntent);
            }
        });
    }
}