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

public class RangerListFormat extends ArrayAdapter<String> {

    private ArrayList<String> ranger;
    private ArrayList<Integer> imgid;
    private Activity context;

    public RangerListFormat(Activity context, ArrayList<String> ranger, ArrayList<Integer> imgid) {
        super(context, R.layout.activity_ranger_list_format, ranger);
        this.context = context;
        this.ranger = ranger;
        this.imgid = imgid;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        RangerListFormat.ViewHolder viewHolder = null;
        if (r == null)
        {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.activity_ranger_list_format, null, true);
            viewHolder = new RangerListFormat.ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (RangerListFormat.ViewHolder) r.getTag();
        }

        viewHolder.ivw.setImageResource(imgid.get(position));
        viewHolder.tvw1.setText(ranger.get(position));

        return r;
    }

    class ViewHolder
    {
        TextView tvw1;
        ImageView ivw;
        ViewHolder(View v)
        {
            tvw1 = (TextView) v.findViewById(R.id.ranger);
            ivw = (ImageView) v.findViewById(R.id.imageView);
        }

    }
}