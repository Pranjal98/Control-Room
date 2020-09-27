package com.pranjal98.contagion2020;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequestViewHolder extends RecyclerView.ViewHolder {

    public TextView stateName;
    public TextView confirmed;
    public TextView active;
    public TextView decreased;
    public TextView recovered;
    public TextView till;
    public RelativeLayout relativeLayout;

    public RequestViewHolder(@NonNull View itemView) {
        super(itemView);

        stateName = itemView.findViewById(R.id.stateName);
        confirmed = itemView.findViewById(R.id.confirmed);
        active = itemView.findViewById(R.id.active);
        decreased = itemView.findViewById(R.id.decreased);
        recovered = itemView.findViewById(R.id.recovered);
        till = itemView.findViewById(R.id.till);
        relativeLayout = itemView.findViewById(R.id.relative_layout);
    }
}
