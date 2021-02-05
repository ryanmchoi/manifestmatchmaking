package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ExistingManifest extends AppCompatActivity {

    private CardView manifestACardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_existing_manifests);

        manifestACardview = findViewById(R.id.manifestAcardview);

        manifestACardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manifestACardview.setBackgroundColor(Color.rgb(223, 241, 255));
                Intent i = new Intent(ExistingManifest.this, ViewManifest.class);
                startActivity(i);
            }
        });
    }
}
