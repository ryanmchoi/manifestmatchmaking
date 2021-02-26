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

public class ViewRanger extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ranger);

        String rangerClicked = getIntent().getStringExtra("Listviewclickvalue");
        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Rangers");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name = dataSnapshot.child(rangerClicked).child("name").getValue(String.class);
                Integer currentUnit = dataSnapshot.child(rangerClicked).child("unit").getValue(Integer.class);
                String currentManifest = dataSnapshot.child(rangerClicked).child("manifest").getValue(String.class);
                String currentStatus = dataSnapshot.child(rangerClicked).child("status").getValue(String.class);

                TextView rangerName = findViewById(R.id.rangerName1);
                TextView rangerManifest = findViewById(R.id.rangerManifest);
                TextView rangerStatus = findViewById(R.id.rangerStatus1);
                TextView rangerUnit = findViewById(R.id.rangerUnit);

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
    }
}