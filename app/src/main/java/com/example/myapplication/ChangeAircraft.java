package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeAircraft extends AppCompatActivity {

    EditText aircraftName;
    EditText aircraftDeparture;
    EditText aircraftLocation;
    EditText aircraftMaxCapacity;
    EditText aircraftStatus;
    Button updateAircraft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_aircraft);

        String manifestClicked = getIntent().getStringExtra("Listviewclickvalue");
        String oldAircraftName = getIntent().getStringExtra("aircraftName");

        aircraftName = findViewById(R.id.aircraft_name_field);
        aircraftDeparture = findViewById(R.id.aircraft_departure_field);
        aircraftLocation = findViewById(R.id.aircraft_location_field);
        aircraftMaxCapacity = findViewById(R.id.aircraft_capacity_field);
        aircraftStatus = findViewById(R.id.aircraft_status_field);
        updateAircraft = findViewById(R.id.update_aircraft_button);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Manifests");
        aircraftName.setText(oldAircraftName);

        updateAircraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkDataEntered()) {

                    //update database
                    String aircraftNameInput = aircraftName.getText().toString();
                    String aircraftDepartureInput = aircraftDeparture.getText().toString();
                    String aircraftLocationInput = aircraftLocation.getText().toString();
                    String aircraftMaxCapacityInput = aircraftMaxCapacity.getText().toString();
                    String aircraftStatusInput = aircraftStatus.getText().toString();

                    mDatabase.child(manifestClicked).child("aircraft_name").setValue(aircraftNameInput);
                    if(!isEmpty(aircraftDeparture)) {
                        mDatabase.child(manifestClicked).child("departure_time").setValue(Integer.parseInt(aircraftDepartureInput));
                    }
                    if(!isEmpty(aircraftLocation)) {
                        mDatabase.child(manifestClicked).child("location").setValue(aircraftLocationInput);
                    }
                    if(!isEmpty(aircraftMaxCapacity)) {
                        mDatabase.child(manifestClicked).child("max_capacity").setValue(Integer.parseInt(aircraftMaxCapacityInput));
                    }
                    if(!isEmpty(aircraftStatus)) {
                        mDatabase.child(manifestClicked).child("status").setValue(aircraftStatusInput);
                    }

                    //finish() goes back to the page with the manifest details on it
                    finish();
                }
            }
        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean checkDataEntered() {
        if (isEmpty(aircraftName)) {
            aircraftName.setError("Aircraft name is required!");
            Toast t = Toast.makeText(this, "You must enter the aircraft name to create one!", Toast.LENGTH_SHORT);
            t.show();
            return false;
        }
        return true;
    }

}