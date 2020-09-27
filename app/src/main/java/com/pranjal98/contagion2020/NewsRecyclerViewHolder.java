package com.pranjal98.contagion2020;

import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView desc;
    public TextView author;
    public TextView published_ad;
    public TextView source;
    public TextView time;
    public ImageView imageView;
    public ProgressBar progressBar;
    public RelativeLayout relativeLayout;

    public NewsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.title);
        desc = itemView.findViewById(R.id.desc);
        author = itemView.findViewById(R.id.author);
        published_ad = itemView.findViewById(R.id.publishAt);
        source = itemView.findViewById(R.id.source);
        time = itemView.findViewById(R.id.time);
        imageView = itemView.findViewById(R.id.img);
        progressBar = itemView.findViewById(R.id.progress_load_photo);
        relativeLayout = itemView.findViewById(R.id.relative_layout);
    }
}
