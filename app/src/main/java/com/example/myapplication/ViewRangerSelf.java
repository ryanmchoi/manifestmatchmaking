package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewRangerSelf extends AppCompatActivity {
    private Button viewAircraftPersonnelBtn;
    private Button viewAircraftDetailsBtn;
    private String currManifest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ranger_self);

        String rangerClicked = getIntent().getStringExtra("rangerName");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Rangers");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child(rangerClicked).child("name").getValue(String.class);
                Integer currentUnit = dataSnapshot.child(rangerClicked).child("unit").getValue(Integer.class);
                String currentManifest = dataSnapshot.child(rangerClicked).child("manifest").getValue(String.class);
                String currentStatus = dataSnapshot.child(rangerClicked).child("status").getValue(String.class);

                currManifest = currentManifest;

                TextView rangerName = findViewById(R.id.rangerSelfName);
                TextView rangerManifest = findViewById(R.id.rangerSelfManifest);
                TextView rangerStatus = findViewById(R.id.rangerSelfStatus);
                TextView rangerUnit = findViewById(R.id.rangerSelfUnit);

                String rangerNameText = "Ranger " +  name;
                String rangerManifestText = "Manifest: " + currentManifest;
                String rangerUnitText = "Unit: " + currentUnit;
                String rangerStatusText = "Status: " + currentStatus;

                //set the text of the textViews
                rangerName.setText(rangerNameText);
                rangerManifest.setText(rangerManifestText);
                rangerStatus.setText(rangerStatusText);
                rangerUnit.setText(rangerUnitText);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });

        viewAircraftPersonnelBtn = findViewById(R.id.rangerAircraftPersonnelButton1);

        if (viewAircraftPersonnelBtn == null) {
            Log.d("hello", "hello");
        }

        viewAircraftPersonnelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAircraftPersonnelIntent = new Intent(ViewRangerSelf.this, RangerList.class);
                viewAircraftPersonnelIntent.putExtra("Listviewclickvalue", currManifest);
                startActivity(viewAircraftPersonnelIntent);
            }
        });

        viewAircraftDetailsBtn = findViewById(R.id.rangerAircraftDetailsButton);
        viewAircraftDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewAircraftDetailsIntent = new Intent(ViewRangerSelf.this, ViewAircraftDetails.class);
                viewAircraftDetailsIntent.putExtra("Listviewclickvalue", currManifest);
                startActivity(viewAircraftDetailsIntent);
            }
        });
    }
}
