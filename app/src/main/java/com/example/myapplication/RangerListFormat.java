package com.example.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class RangerListFormat extends ArrayAdapter<String> implements Filterable {

    private ArrayList<String> ranger;
    private ArrayList<String> orig;
    private ArrayList<Integer> imgid;
    private Activity context;

    public RangerListFormat(Activity context, ArrayList<String> ranger, ArrayList<Integer> imgid) {
        super(context, R.layout.activity_ranger_list_format, ranger);
        this.context = context;
        this.ranger = ranger;
        this.imgid = imgid;
    }

    public Filter getFilter() {
        return new Filter() {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                final FilterResults oReturn = new FilterResults();
                final ArrayList<String> results = new ArrayList<String>();
                if (orig == null)
                    orig = ranger;
                if (constraint != null) {
                    if (orig != null && orig.size() > 0) {
                        for (String g : orig) {
                            if (g.contains(constraint.toString()))
                                results.add(g);
                        }
                    }
                    oReturn.values = results;
                }
                return oReturn;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                ranger = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return ranger.size();
    }

    @Override
    public String getItem(int position) {
        return ranger.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
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