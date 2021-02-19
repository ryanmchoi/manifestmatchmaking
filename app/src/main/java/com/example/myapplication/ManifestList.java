package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models.Manifest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ManifestList extends AppCompatActivity {
    DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");
    
    String[] manifest = new String[]{"Manifest A", "Manifest B", "Manifest C", "Manifest D", "Manifest E", "Manifest F"};
    ListView lst;

    protected void onCreate(Bundle savedInstanceState) {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Manifest> map = (Map<String, Manifest>) dataSnapshot.getValue();
                List<String> mList = new ArrayList<>();
                for (String key : map.keySet()) {
                    mList.add(key);
                }

                Log.d("keys", mList.toString());

                Log.d("Manifest A Details", "" + map.get("A"));
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifest_list);
        this.setTitle("Manifests");
        lst = (ListView) findViewById(R.id.listview);
        ManifestListFormat customListview = new ManifestListFormat(this, manifest);
        lst.setAdapter(customListview);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mview = manifest[position].toString();
                Intent intent = new Intent(ManifestList.this, ViewManifest.class);
                intent.putExtra("Listviewclickvalue", mview);
                startActivity(intent);
            }
        });

    }

}