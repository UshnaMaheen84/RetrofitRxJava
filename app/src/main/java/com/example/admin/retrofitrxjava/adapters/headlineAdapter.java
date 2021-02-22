package com.example.admin.retrofitrxjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.retrofitrxjava.R;
import com.example.admin.retrofitrxjava.models.Article;
import com.example.admin.retrofitrxjava.models.Source;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class headlineAdapter extends RecyclerView.Adapter<headlineAdapter.MyViewHolder> {
    List<Article> arrayList = new ArrayList<>();
    Context context;

    public headlineAdapter(List<Article> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public headlineAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headline_items_view, parent, false);

        return new headlineAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(headlineAdapter.MyViewHolder holder, int position) {
        final Article object = arrayList.get(position);
        holder.title.setText(object.getTitle());
        holder.auther.setText(object.getAuthor());
        holder.discription.setText(object.getDescription());
        holder.content.setText(object.getContent());
        holder.date.setText(object.getPublishedAt().substring(0,10));

        if(object.getDescription() == null){
            holder.descriptionLayout.setVisibility(View.GONE);
        }
        if(object.getContent() == null){
            holder.contentLayout.setVisibility(View.GONE);
        }

        Picasso.get().load(object.getUrlToImage()).into(holder.imageView);

        holder.holderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(object.url));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView auther, title, discription, date, content;
        View holderView;
        ImageView imageView;
        LinearLayout descriptionLayout, contentLayout;
        public MyViewHolder(View view) {
            super(view);

            holderView = view;
            imageView= view.findViewById(R.id.image);
            descriptionLayout= view.findViewById(R.id.descriptionLayout);
            contentLayout= view.findViewById(R.id.contentLayout);
            auther = (TextView) view.findViewById(R.id.author);
            title = (TextView) view.findViewById(R.id.title);
            discription = (TextView) view.findViewById(R.id.description);
            content= view.findViewById(R.id.content);
            date = (TextView) view.findViewById(R.id.publishedAt);
        }
    }

}
