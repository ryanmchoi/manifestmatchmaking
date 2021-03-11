package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    private Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton = findViewById(R.id.loginButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //database check to see if password is correct
                //also redirect screens based on if they are DACO or not

                //if they are a DACO then go to the screen to view manifests and add manifests
                Intent loginIntent = new Intent(Login.this, MainActivity.class);
                startActivity(loginIntent);
            }
        });


    }
}