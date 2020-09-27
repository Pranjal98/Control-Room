package com.pranjal98.contagion2020;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RequestViewHolder> {

    private List<StateClass> listItems;
    private Context context;
    private Integer check;

    public RecyclerViewAdapter(List<StateClass> listItems, Context context, Integer number) {
        this.listItems = listItems;
        this.context = context;
        this.check = number;
    }

    @NonNull
    @Override
    public RequestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);

        return new RequestViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RequestViewHolder holder, int position) {
        final StateClass listItem = listItems.get(position);

        holder.stateName.setText(listItem.getRequestsID());
        holder.active.setText("Active\n" + listItem.getDevice());
        holder.confirmed.setText(listItem.getQuantity());
        holder.decreased.setText("Deceased\n" + listItem.getDate());
        holder.recovered.setText("Recovered\n" + listItem.getStatus());
        holder.till.setText("Last updated time : " + listItem.getTill());

        if (check == 2)
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DistrictDataActivity.class);
                    intent.putExtra("stateName", listItem.getRequestsID());
                    intent.putExtra("confirmed", listItem.getQuantity());
                    intent.putExtra("active", listItem.getDevice());
                    intent.putExtra("decreased", listItem.getDate());
                    intent.putExtra("recovered", listItem.getStatus());
                    intent.putExtra("till", listItem.getTill());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
