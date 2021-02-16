package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewManifest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_manifest);

        //bring in all the textViews from the activity_view_manifest xml file
        TextView manifestName = findViewById(R.id.manifest_label);
        TextView aircraftName = findViewById(R.id.aircraft_name_label);
        TextView location = findViewById(R.id.aircraft_location_label);
        TextView departureTime = findViewById(R.id.departure_time_label);
        TextView capacity = findViewById(R.id.capacity_label);
        TextView status = findViewById(R.id.status_label);

        //Replace the existing text of textView with that of the information retrieved from database
        //Right now, these are dummy values
        String manifestText = manifestName.getText() + " A";
        String aircraftText = aircraftName.getText() + " D";
        String locationText = location.getText() + " Hangar C";
        String departText = departureTime.getText() + " 14:00";
        String capacityText = capacity.getText() + " 21";
        String statusText = status.getText() + " Functioning";

        //set the text of the textViews
        manifestName.setText(manifestText);
        aircraftName.setText(aircraftText);
        location.setText(locationText);
        departureTime.setText(departText);
        capacity.setText(capacityText);
        status.setText(statusText);


        //Gets which manifest was clicked
        Intent intent = getIntent();
        String text = intent.getStringExtra("Listviewclickvalue");
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }
}