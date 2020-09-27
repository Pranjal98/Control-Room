package com.pranjal98.contagion2020;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewHolder> {

    private List<Article> listItems;
    private Context context;
    private Integer check;

    public NewsRecyclerViewAdapter(List<Article> listItems, Context context, Integer number) {
        this.listItems = listItems;
        this.context = context;
        this.check = number;
    }

    @NonNull
    @Override
    public NewsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout_news, parent, false);

        return new NewsRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewHolder holder, int position) {
        final Article listItem = listItems.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(context)
                .load(listItem.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.title.setText(listItem.getTitle());
        holder.desc.setText(listItem.getDescription());
        holder.source.setText(listItem.getSource().getName());
        holder.time.setText("\u2022" + listItem.getPublishedAt());
        holder.published_ad.setText(listItem.getPublishedAt());
        holder.author.setText(listItem.getAuthor());

        if (check == 2)
            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NewsDetailsActivity.class);
                    intent.putExtra("url", listItem.getUrl());
                    intent.putExtra("title", listItem.getTitle());
                    intent.putExtra("img", listItem.getUrlToImage());
                    intent.putExtra("date", listItem.getPublishedAt());
                    intent.putExtra("source", listItem.getSource().getName());
                    intent.putExtra("author", listItem.getAuthor());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }
}
