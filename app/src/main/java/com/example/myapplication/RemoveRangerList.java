package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.models.Ranger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class RemoveRangerList extends AppCompatActivity {

    DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Rangers");
    ListView lst;
    ArrayList<String> ranger = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_ranger_list);

        String currManifest = getIntent().getStringExtra("Listviewclickvalue");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Ranger> map = (Map<String, Ranger>) dataSnapshot.getValue();
                ArrayList<String> mList = new ArrayList<>();
                for (String key : map.keySet()) {
                    mList.add(key);
                    String rangerManifest = dataSnapshot.child(key).child("manifest").getValue(String.class);
                    if (rangerManifest.compareTo(currManifest) == 0) {
                        ranger.add(key);
                    }
                }

                lst = (ListView) findViewById(R.id.listview2);
                ManifestListFormat customListview = new ManifestListFormat(RemoveRangerList.this, ranger);
                lst.setAdapter(customListview);
                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String rview = ranger.get(position).toString();
                        mDatabase.child(rview).child("manifest").setValue("None");
                        ranger.clear();
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Ranger Error Tag", "loadRanger:onCancelled", databaseError.toException());
            }
        });

    }
}