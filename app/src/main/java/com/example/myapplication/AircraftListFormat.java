package com.example.myapplication;

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

public class AircraftListFormat extends ArrayAdapter<String> {

    private ArrayList<String> aircraft;
    private Activity context;
    private ArrayList<Integer> imgid;

    public AircraftListFormat(Activity context, ArrayList<String> aircraft, ArrayList<Integer> imgid) {
        super(context, R.layout.activity_aircraft_list_format, aircraft);
        this.context = context;
        this.aircraft = aircraft;
        this.imgid = imgid;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        AircraftListFormat.ViewHolder viewHolder = null;
        if (r == null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_aircraft_list_format, null, true);
            viewHolder = new AircraftListFormat.ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (AircraftListFormat.ViewHolder) r.getTag();
        }

        viewHolder.ivw.setImageResource(imgid.get(position));
        viewHolder.tvw1.setText(aircraft.get(position));

        return r;
    }

    class ViewHolder
    {
        TextView tvw1;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvw1 = (TextView) v.findViewById(R.id.aircraft);
            ivw = (ImageView) v.findViewById(R.id.imageView);
        }

    }
}
