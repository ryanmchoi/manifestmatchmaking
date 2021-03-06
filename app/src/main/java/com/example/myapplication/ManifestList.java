package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.myapplication.models.Manifest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class ManifestList extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");
    ListView lst;
    ArrayList<String> manifest = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifest_list);

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Manifest> map = (Map<String, Manifest>) dataSnapshot.getValue();
                ArrayList<String> mList = new ArrayList<>();
                for (String key : map.keySet()) {
                    mList.add(key);
                    manifest.add(key);
                }
                lst = (ListView) findViewById(R.id.listview2);
                ManifestListFormat customListview = new ManifestListFormat(ManifestList.this, manifest);
                lst.setAdapter(customListview);
                Log.d("keys", mList.toString());
                Log.d("Manifest A Details", "" + map.get("A"));
                String test = dataSnapshot.child("A").child("status").getValue(String.class);
                Log.d("Status", "" + test);

                lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String mview = manifest.get(position).toString();
                        Intent intent = new Intent(ManifestList.this, ViewManifest.class);
                        intent.putExtra("Listviewclickvalue", mview);
                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });
        this.setTitle("Manifests");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}