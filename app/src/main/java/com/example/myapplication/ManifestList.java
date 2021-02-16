package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class ManifestList extends AppCompatActivity {

    ListView lst;
    String[] manifest = new String[]{"Manifest A", "Manifest B", "Manifest C", "Manifest D", "Manifest E", "Manifest F"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifest_list);
        this.setTitle("Manifests");
        lst = (ListView) findViewById(R.id.listview);
        ManifestListFormat customListview = new ManifestListFormat(this, manifest);
        lst.setAdapter(customListview);


        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String mview = manifest[position].toString();
                Intent intent = new Intent(ManifestList.this, ViewManifest.class);
                intent.putExtra("Listviewclickvalue", mview);
                startActivity(intent);
            }
        });

    }

}