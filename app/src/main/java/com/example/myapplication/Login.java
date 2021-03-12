package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Button LoginButton;
    private TextView Username;
    private TextView Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginButton = findViewById(R.id.loginButton);
        Username = findViewById(R.id.LoginUsernameEditText);
        Password = findViewById(R.id.loginPasswordEditText);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //database check to see if password is correct
                //also redirect screens based on if they are DACO or not
                DatabaseReference mDatabase = FirebaseDatabase.getInstance("https://manifest-matchmaking-default-rtdb.firebaseio.com/").getReference("Users");
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String password = dataSnapshot.child(Username.getText().toString()).child("password").getValue(String.class);
                        Integer isDaco = dataSnapshot.child(Username.getText().toString()).child("isDaco").getValue(Integer.class);
                        String rangerName = dataSnapshot.child(Username.getText().toString()).child("Ranger").getValue(String.class);

                        if (password.equals(Password.getText().toString())) {
                            if (isDaco == 1) {
                                Intent loginIntent = new Intent(Login.this, MainActivity.class);
                                startActivity(loginIntent);
                            } else {
                                Intent loginIntent = new Intent(Login.this, ViewRangerSelf.class);
                                loginIntent.putExtra("rangerName", rangerName);
                                startActivity(loginIntent);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("Manifest Error Tag", "loadManifest:onCancelled", databaseError.toException());
                    }
                });

                //if they are a DACO then go to the screen to view manifests and add manifests

            }
        });


    }
}