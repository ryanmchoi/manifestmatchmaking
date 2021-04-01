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
    Button viewAircraftDetailsButton;
    Button removeRangerButton;
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

                //bring in all the textViews from the activity_view_manifest xml file
                TextView manifestNameTextView = findViewById(R.id.manifest_label);
                TextView aircraftNameTextView = findViewById(R.id.aircraft_name_label);

                //Replace the existing text of textView with that of the information retrieved from database
                String manifestText = "Manifest " + manifestClicked;
                String aircraftText = "Aircraft: " + aircraftName;

                //set the text of the textViews
                manifestNameTextView.setText(manifestText);
                aircraftNameTextView.setText(aircraftText);
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
                Intent changeAircraftIntent = new Intent(ViewManifest.this, AircraftList.class);
                changeAircraftIntent.putExtra("Listviewclickvalue", manifestClicked);
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

        removeRangerButton = findViewById(R.id.removeRangerButton);
        removeRangerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createRangerIntent = new Intent(ViewManifest.this, RemoveRangerList.class);
                createRangerIntent.putExtra("Listviewclickvalue", manifestClicked);
                startActivity(createRangerIntent);
            }
        });

        viewAircraftDetailsButton = findViewById(R.id.viewAircraftDetailsButton);
        viewAircraftDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aircraftDetailsIntent = new Intent(ViewManifest.this, DacoViewAircraftDetails.class);
                aircraftDetailsIntent.putExtra("Listviewclickvalue", manifestClicked);
                startActivity(aircraftDetailsIntent);
            }
        });
    }
}