package com.example.guest.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RoutesAdapter extends ArrayAdapter<Route> {
    private static class ViewHolder {
        TextView title;
        TextView type;
        TextView duration;
        TextView distance;
        TextView cost;
    }

    public RoutesAdapter(Context context, ArrayList<Route> routes) {
        super(context, R.layout.route, routes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Route route = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.route, parent, false);
            viewHolder.title = convertView.findViewById(R.id.title);
            viewHolder.type = convertView.findViewById(R.id.type);
            viewHolder.duration = convertView.findViewById(R.id.duration);
            viewHolder.distance = convertView.findViewById(R.id.distance);
            viewHolder.cost = convertView.findViewById(R.id.cost);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.title.setText("Titlu: " + route.getName());
        viewHolder.type.setText("Tip traseu: " + route.getType());
        viewHolder.duration.setText("Durata: " + route.getDuration());
        viewHolder.distance.setText("Distanta: " + route.getDistance());
        viewHolder.cost.setText("Cost:" + route.getCost());
        return convertView;
    }
}