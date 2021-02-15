package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button createManifestBtn;
    private Button viewManifestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createManifestBtn = findViewById(R.id.createManifestBtn);
        viewManifestBtn = findViewById(R.id.viewManifestBtn);

        createManifestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createManifestIntent = new Intent(MainActivity.this, CreateManifest.class);
                startActivity(createManifestIntent);
            }
        });

        viewManifestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewManifestIntent = new Intent(MainActivity.this, ManifestList.class);
                startActivity(viewManifestIntent);
            }
        });
    }
}