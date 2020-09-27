package com.pranjal98.contagion2020;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResourcesRecyclerViewAdapter extends RecyclerView.Adapter<ResourceRecyclerViewHolder> {

    private List<ResourceClass> listItems;
    private Context context;
    private Integer check;

    public ResourcesRecyclerViewAdapter(List<ResourceClass> listItems, Context context, Integer number) {
        this.listItems = listItems;
        this.context = context;
        this.check = number;
    }

    @NonNull
    @Override
    public ResourceRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_three, parent, false);

        return new ResourceRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceRecyclerViewHolder holder, int position) {
        final ResourceClass listItem = listItems.get(position);

        holder.category.setText(listItem.getCategory());
        holder.city.setText(listItem.getCity() + ",");
        holder.contact.setText(listItem.getContact());
        holder.descriptionandorserviceprovided.setText(listItem.getDescriptionandorserviceprovided());
        holder.nameoftheorganisation.setText(listItem.getNameoftheorganisation());
        holder.phonenumber.setText(listItem.getPhonenumber());
        holder.state.setText(listItem.getState());

//        if (check == 2)
//            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(context, DistrictDataActivity.class);
//                    intent.putExtra("stateName", listItem.getRequestsID());
//                    intent.putExtra("confirmed", listItem.getQuantity());
//                    intent.putExtra("active", listItem.getDevice());
//                    intent.putExtra("decreased", listItem.getDate());
//                    intent.putExtra("recovered", listItem.getStatus());
//                    intent.putExtra("till", listItem.getTill());
//                    context.startActivity(intent);
//                }
//            });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
