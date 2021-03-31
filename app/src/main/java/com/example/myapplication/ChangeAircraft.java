package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangeAircraft extends AppCompatActivity {

    TextView aircraftName;
    TextView aircraftDeparture;
    TextView aircraftLocation;
    TextView aircraftMaxCapacity;
    TextView aircraftStatus;
    Button confirmBtn;
    Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_aircraft);

        String aircraftClicked = getIntent().getStringExtra("AircraftClicked");
        String manifestClicked = getIntent().getStringExtra("ManifestClicked");

        aircraftName = findViewById(R.id.aircraft_name);
        aircraftDeparture = findViewById(R.id.departureTimeLabel);
        aircraftLocation = findViewById(R.id.aircraftLocationLabel);
        aircraftMaxCapacity = findViewById(R.id.maxCapacityLabel);
        aircraftStatus = findViewById(R.id.statusLabel);
        confirmBtn = findViewById(R.id.confirmButton);
        cancelBtn = findViewById(R.id.cancelButton);

        DatabaseReference aDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Aircrafts");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");

        //populate all the fields from database
        aircraftName.setText("Aircraft: " + aircraftClicked);


        aDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String aircraftAssigned = dataSnapshot.child(aircraftClicked).child("manifest_name").getValue(String.class);

                //get values from database for the manifest that was clicked
                Integer departureTime = dataSnapshot.child(aircraftClicked).child("departure_time").getValue(Integer.class);
                String location = dataSnapshot.child(aircraftClicked).child("location").getValue(String.class);
                Integer maxCapacity = dataSnapshot.child(aircraftClicked).child("max_capacity").getValue(Integer.class);
                String status = dataSnapshot.child(aircraftClicked).child("status").getValue(String.class);

                //Replace the existing text of textView with that of the information retrieved from database
                String locationText = "Location: " + location;
                String departText = "Departure Time: " + departureTime;
                String capacityText = "Max Capacity: " + maxCapacity;
                String statusText = "Status: " + status;

                //set the text of the textViews
                aircraftLocation.setText(locationText);
                aircraftDeparture.setText(departText);
                aircraftMaxCapacity.setText(capacityText);
                aircraftStatus.setText(statusText);


                confirmBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (aircraftAssigned.equals("n/a")) {
                            //update the value in the aircrafts table
                            aDatabase.child(aircraftClicked).child("manifest_name").setValue(manifestClicked);
                            //update the value in the manifests table
                            mDatabase.child(manifestClicked).child("aircraft_name").setValue(aircraftClicked);
                            Intent intent = new Intent(ChangeAircraft.this, ViewManifest.class);
                            intent.putExtra("Listviewclickvalue", manifestClicked);
                            startActivity(intent);
                        } else {
                            Toast fail = Toast.makeText(getApplicationContext(), "This aircraft is already assigned to a manifest", Toast.LENGTH_LONG);
                            fail.show();
                        }
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Aircraft Error Tag", "loadAircraft:onCancelled", databaseError.toException());
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangeAircraft.this, AircraftList.class);
                intent.putExtra("Listviewclickvalue", manifestClicked);
                startActivity(intent);
            }
        });

    }
}