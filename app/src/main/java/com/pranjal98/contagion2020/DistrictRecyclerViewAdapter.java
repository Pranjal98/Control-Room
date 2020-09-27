package com.pranjal98.contagion2020;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DistrictRecyclerViewAdapter extends RecyclerView.Adapter<DistrictRecyclerViewHolder> {

    private List<DistrictClass> listItems;
    private Context context;
    private Integer check;

    public DistrictRecyclerViewAdapter(List<DistrictClass> listItems, Context context, Integer number) {
        this.listItems = listItems;
        this.context = context;
        this.check = number;
    }

    @NonNull
    @Override
    public DistrictRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_two, parent, false);

        return new DistrictRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DistrictRecyclerViewHolder holder, int position) {
        final DistrictClass listItem = listItems.get(position);

        holder.stateName.setText(listItem.getRequestsID());
        holder.active.setText("Active\n" + listItem.getDevice());
        holder.confirmed.setText(listItem.getQuantity());
        holder.decreased.setText("Deceased\n" + listItem.getDate());
        holder.recovered.setText("Recovered\n" + listItem.getStatus());
        holder.till.setText(listItem.getZone() + " zone till " + listItem.getTill());

        if(listItem.getZone().equals("Orange")) {
            holder.till.setTextColor(context.getResources().getColor(R.color.orange));
            holder.view.setBackground(ContextCompat.getDrawable(context, R.drawable.orange_zoned));
        }
        else if(listItem.getZone().equals("Red")) {
            holder.till.setTextColor(context.getResources().getColor(R.color.red));
            holder.view.setBackground(ContextCompat.getDrawable(context, R.drawable.red_zoned));
        }
        else {
            holder.till.setTextColor(context.getResources().getColor(R.color.green));
            holder.view.setBackground(ContextCompat.getDrawable(context, R.drawable.green_zoned));
        }
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
