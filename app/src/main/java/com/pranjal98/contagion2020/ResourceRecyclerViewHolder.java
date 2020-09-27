package com.pranjal98.contagion2020;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ResourceRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView category;
    public TextView city;
    public TextView contact;
    public TextView descriptionandorserviceprovided;
    public TextView nameoftheorganisation;
    public TextView phonenumber;
    public TextView state;
    public RelativeLayout relativeLayout;

    public ResourceRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        category = itemView.findViewById(R.id.category);
        city = itemView.findViewById(R.id.city);
        contact = itemView.findViewById(R.id.contact);
        descriptionandorserviceprovided = itemView.findViewById(R.id.descriptionandorserviceprovided);
        nameoftheorganisation = itemView.findViewById(R.id.nameoftheorganisation);
        phonenumber = itemView.findViewById(R.id.phonenumber);
        state = itemView.findViewById(R.id.stateName);
        relativeLayout = itemView.findViewById(R.id.relative_layout);
    }
}
