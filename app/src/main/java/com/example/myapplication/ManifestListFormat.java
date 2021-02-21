package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ManifestListFormat extends ArrayAdapter<String> {

    private ArrayList<String> manifest;
    private ArrayList<Integer> imgid;
    private Activity context;

    public ManifestListFormat(Activity context, ArrayList<String> manifest, ArrayList<Integer> imgid) {
        super(context, R.layout.activity_manifest_list_format, manifest);
        this.context = context;
        this.manifest = manifest;
        this.imgid = imgid;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_manifest_list_format, null, true);
            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }

        viewHolder.ivw.setImageResource(imgid.get(position));
        viewHolder.tvw1.setText(manifest.get(position));

        return r;
    }

    class ViewHolder
    {
        TextView tvw1;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvw1 = (TextView) v.findViewById(R.id.manifest);
            ivw = (ImageView) v.findViewById(R.id.imageView);
        }

    }
}