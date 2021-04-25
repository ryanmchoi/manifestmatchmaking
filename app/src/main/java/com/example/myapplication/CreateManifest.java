package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.models.Manifest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CreateManifest extends AppCompatActivity {

    EditText manifestName;
    EditText aircraftName;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create Manifest");
        setContentView(R.layout.activity_create_manifest2);

        //back button
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        manifestName = findViewById(R.id.manifestName);
        aircraftName = findViewById(R.id.aircraftName);
        create = findViewById(R.id.create);

        ArrayList<String> manifest_names = new ArrayList<>();

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");

        //Need the names of the manifest so that user cannot create a manifest with the same name
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Manifest> map = (Map<String, Manifest>) dataSnapshot.getValue();
                for (String key : map.keySet()) {
                    manifest_names.add(key);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkDataEntered()) {

                    //get values that were inputted by user
                    String aircraft_name = aircraftName.getText().toString();
                    String id = manifestName.getText().toString();

                    Manifest newManifest = new Manifest(aircraft_name, id);
                    if (!manifest_names.contains(id)) {
                        //create new record in database
                        mDatabase.child(id).setValue(newManifest);
                        //manifest successfully added
                        Toast success = Toast.makeText(getApplicationContext(), "Successfully created Manifest", Toast.LENGTH_LONG);
                        success.show();

                        Intent viewManifestIntent = new Intent(CreateManifest.this, ManifestList.class);
                        startActivity(viewManifestIntent);
                    } else {
                        //Could not add new manifest
                        Toast fail = Toast.makeText(getApplicationContext(), "There is already a manifest with this name", Toast.LENGTH_LONG);
                        fail.show();
                    }

                }
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        if (isEmpty(manifestName)) {
            manifestName.setError("Manifest name is required!");
            Toast t = Toast.makeText(this, "You must enter the manifest name to create one!", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
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
