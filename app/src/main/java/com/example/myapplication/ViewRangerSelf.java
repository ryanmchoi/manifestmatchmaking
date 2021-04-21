package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        DatabaseReference rDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Rangers").child(rangerClicked);

        rDatabase.addValueEventListener(new ValueEventListener() {
            boolean flag = false;
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(flag) {
                    Toast alert = Toast.makeText(getApplicationContext(), "There has been an update to your assignment", Toast.LENGTH_LONG);
                    alert.show();
                } else {
                    flag = true;
                }


                String name = dataSnapshot.child("name").getValue(String.class);
                Integer currentUnit = dataSnapshot.child("unit").getValue(Integer.class);
                String currentManifest = dataSnapshot.child("manifest").getValue(String.class);
                String currentStatus = dataSnapshot.child("status").getValue(String.class);

                currManifest = currentManifest;

                TextView rangerName = findViewById(R.id.rangerSelfName);
                TextView rangerManifest = findViewById(R.id.rangerSelfManifest);
                TextView rangerStatus = findViewById(R.id.rangerSelfStatus);
                TextView rangerUnit = findViewById(R.id.rangerSelfUnit);

                String rangerNameText = "Ranger " + name;
                String rangerManifestText = "Manifest: " + currManifest;
                String rangerUnitText = "Unit: " + currentUnit;
                String rangerStatusText = "Status: " + currentStatus;

                //set the text of the textViews
                rangerName.setText(rangerNameText);
                rangerManifest.setText(rangerManifestText);
                rangerStatus.setText(rangerStatusText);
                rangerUnit.setText(rangerUnitText);


                DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests").child(currManifest);

                mDatabase.addValueEventListener(new ValueEventListener() {
                    boolean flag = false;

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if(!currManifest.equals("None")) {
                            String aircraft = dataSnapshot.child("aircraft_name").getValue(String.class);
                            DatabaseReference aDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Aircrafts").child(aircraft);

                            aDatabase.addValueEventListener(new ValueEventListener() {
                                boolean flag2 = false;

                                @Override
                                public void onDataChange(DataSnapshot datasnapshot) {
                                    if (flag2) {
                                        Toast alert = Toast.makeText(getApplicationContext(), "There has been an update to your assignment", Toast.LENGTH_LONG);
                                        alert.show();
                                    } else {
                                        flag2 = true;
                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
                                }
                            });
                        }

                        if(flag) {
                            Toast alert = Toast.makeText(getApplicationContext(), "There has been an update to your assignment", Toast.LENGTH_LONG);
                            alert.show();
                        } else{
                            flag = true;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
                    }
                });


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
                if(!currManifest.equals("None")) {
                    Intent viewAircraftPersonnelIntent = new Intent(ViewRangerSelf.this, RangerList.class);
                    viewAircraftPersonnelIntent.putExtra("Listviewclickvalue", currManifest);
                    startActivity(viewAircraftPersonnelIntent);
                } else {
                    Toast alert = Toast.makeText(getApplicationContext(), "You are not assigned a manifest", Toast.LENGTH_LONG);
                    alert.show();
                }
            }
        });

        viewAircraftDetailsBtn = findViewById(R.id.rangerAircraftDetailsButton);
        viewAircraftDetailsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!currManifest.equals("None")) {
                    Intent viewAircraftDetailsIntent = new Intent(ViewRangerSelf.this, ViewAircraftDetails.class);
                    viewAircraftDetailsIntent.putExtra("Listviewclickvalue", currManifest);
                    startActivity(viewAircraftDetailsIntent);
                } else {
                    Toast alert = Toast.makeText(getApplicationContext(), "You are not assigned a manifest", Toast.LENGTH_LONG);
                    alert.show();
                }
            }
        });
    }
}
