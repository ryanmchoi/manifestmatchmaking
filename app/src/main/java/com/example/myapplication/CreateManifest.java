package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateManifest extends AppCompatActivity {

    EditText manifestName;
    EditText aircraftName;
    EditText departure;
    EditText location;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Create Manifest");
        setContentView(R.layout.activity_create_manifest2);

        manifestName = findViewById(R.id.manifestName);
        aircraftName = findViewById(R.id.aircraftName);
        departure = findViewById(R.id.departure);
        location = findViewById(R.id.location);
        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(manifestName)) {
            manifestName.setError("Manifest name is required!");
            Toast t = Toast.makeText(this, "You must enter the manifest name to create one!", Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
