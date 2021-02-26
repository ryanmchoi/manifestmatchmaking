package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.models.Ranger;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CreateRanger extends AppCompatActivity {
    EditText manifestName;
    EditText rangerName;
    EditText rangerStatus;
    EditText unit;
    Button createRanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create Ranger");
        setContentView(R.layout.activity_create_ranger);

        manifestName = findViewById(R.id.manName);
        rangerName = findViewById(R.id.rangerName);
        rangerStatus = findViewById(R.id.rangerStatus);
        unit = findViewById(R.id.unit);
        createRanger = findViewById(R.id.createRanger);

        String manifestClicked = getIntent().getStringExtra("Listviewclickvalue");

        ArrayList<String> ranger_names = new ArrayList<>();

        DatabaseReference rDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Rangers");

        //Need the names of the ranger so that user cannot create a ranger with the same name
        rDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String, Ranger> map = (Map<String, Ranger>) dataSnapshot.getValue();
                for (String key : map.keySet()) {
                    ranger_names.add(key);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
            }
        });

        createRanger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkDataEntered()) {

                    //get values that were inputted by user
                    String manifest_name = manifestName.getText().toString();
                    String ranger_name = rangerName.getText().toString();
                    String _status = rangerStatus.getText().toString();
                    int _unit = Integer.parseInt(unit.getText().toString());


                    Ranger newRanger= new Ranger(manifest_name, ranger_name, _status, _unit);
                    //if (!ranger_names.contains(ranger_name)) {
                        //create new record in database
                        rDatabase.child(ranger_name).setValue(newRanger);
                        //manifest successfully added
                        Toast success = Toast.makeText(getApplicationContext(), "Successfully created Ranger", Toast.LENGTH_LONG);
                        success.show();

                        Intent viewRangerIntent = new Intent(CreateRanger.this, RangerList.class);
                        startActivity(viewRangerIntent);
                    //} else {
                        //Could not add new manifest
                        //Toast fail = Toast.makeText(getApplicationContext(), "There is already a ranger with this name", Toast.LENGTH_LONG);
                        //fail.show();
                    //}

                    Intent viewManifestIntent = new Intent(CreateRanger.this, ViewManifest.class);
                    viewManifestIntent.putExtra("Listviewclickvalue", manifestClicked);
                    startActivity(viewManifestIntent);
                }
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        if (isEmpty(manifestName) || isEmpty(rangerName)) {
            manifestName.setError("Manifest name and Ranger name is required!");
            Toast t = Toast.makeText(this, "You must enter the manifest name and the range nameto create one!", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }
}
