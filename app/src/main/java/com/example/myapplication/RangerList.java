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

public class RangerList extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Rangers");
    ListView lst;
    ArrayList<String> ranger = new ArrayList<>();
    ArrayList<Integer> imgid = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranger_list);
        String manifestClicked = getIntent().getStringExtra("Listviewclickvalue");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Ranger> map = (Map<String, Ranger>) dataSnapshot.getValue();
                ArrayList<String> mList = new ArrayList<>();
                for (String key : map.keySet()) {
                    mList.add(key);
                    String rangerManifest = dataSnapshot.child(key).child("manifest").getValue(String.class);
                    if (rangerManifest.compareTo(manifestClicked) == 0) {
                        ranger.add(key);
                        String status = dataSnapshot.child(key).child("status").getValue(String.class);
                        if (status.compareTo("Active") == 0) {
                            imgid.add(R.drawable.green);
                        } else {
                            imgid.add(R.drawable.red);
                        }
                    }
                }
                lst = (ListView) findViewById(R.id.listview2);
                RangerListFormat customListview = new RangerListFormat(RangerList.this, ranger, imgid);
                lst.setAdapter(customListview);
                Log.d("keys", mList.toString());
                Log.d("Ranger A Details", "" + map.get("A"));
                String test = dataSnapshot.child("A").child("status").getValue(String.class);
                Log.d("Status", "" + test);

                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String rview = ranger.get(position).toString();
                        Intent intent = new Intent(RangerList.this, ViewRanger.class);
                        intent.putExtra("Listviewclickvalue", rview);
                        startActivity(intent);
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Ranger Error Tag", "loadRanger:onCancelled", databaseError.toException());
            }
        });
        this.setTitle("Rangers");
    }
}