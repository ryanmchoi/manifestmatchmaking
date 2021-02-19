package com.example.myapplication;

import androidx.annotation.NonNull;

import com.example.myapplication.models.Ranger;
import com.example.myapplication.models.Manifest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceManifests;
    private DatabaseReference mReferenceRangers;
    private List<Manifest> manifests = new ArrayList<>();
    private List<Ranger> rangers = new ArrayList<>();

    public interface DataStatus {
        void DataIsLoadedRanger(List<Ranger> rangers, List<String> keys);
        void DataIsLoadedManifest(List<Manifest> manifests, List<String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }

    public FirebaseDatabaseHelper() {
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceRangers = mDatabase.getReference("Rangers");
        mReferenceManifests = mDatabase.getReference("Manifests");
    }

    public void readRangers(final DataStatus dataStatus) {
        mReferenceRangers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                rangers.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Ranger ranger = keyNode.getValue(Ranger.class);
                    rangers.add(ranger);
                }
                dataStatus.DataIsLoadedRanger(rangers, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void readManifests(final DataStatus dataStatus) {
        mReferenceManifests.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                manifests.clear();
                List<String> keys = new ArrayList<>();

                for (DataSnapshot keyNode : snapshot.getChildren()) {
                    keys.add(keyNode.getKey());
                    Manifest manifest = keyNode.getValue(Manifest.class);
                    manifests.add(manifest);
                }
                dataStatus.DataIsLoadedManifest(manifests, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
