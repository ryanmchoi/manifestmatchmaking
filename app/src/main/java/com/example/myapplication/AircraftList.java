package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.models.Aircraft;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class AircraftList extends AppCompatActivity {

    DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Aircrafts");
    ListView lst;
    ArrayList<String> aircraft = new ArrayList<>();
    ArrayList<Integer> imgid = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aircraft_list);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Aircraft> map = (Map<String, Aircraft>) dataSnapshot.getValue();
                ArrayList<String> mList = new ArrayList<>();
                for (String key : map.keySet()) {
                    mList.add(key);
                    aircraft.add(key);
                    String status = dataSnapshot.child(key).child("status").getValue(String.class);
                    if (status.compareTo("Functional") == 0) {
                        imgid.add(R.drawable.green);
                    } else {
                        imgid.add(R.drawable.red);
                    }
                }
                lst = (ListView) findViewById(R.id.listview2);
                AircraftListFormat customListview = new AircraftListFormat(AircraftList.this, aircraft, imgid);
                lst.setAdapter(customListview);
                Log.d("keys", mList.toString());
                Log.d("Aircraft C Details", "" + map.get("C"));
                String test = dataSnapshot.child("C").child("status").getValue(String.class);
                Log.d("Status", "" + test);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Aircraft Error Tag", "loadAircraft:onCancelled", databaseError.toException());
            }
        });
        this.setTitle("Aircrafts");
    }
}