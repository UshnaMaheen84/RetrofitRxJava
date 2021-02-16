package com.example.admin.retrofitrxjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.retrofitrxjava.MainActivity;
import com.example.admin.retrofitrxjava.R;
import com.example.admin.retrofitrxjava.headlines;
import com.example.admin.retrofitrxjava.models.Source;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    List<Source> arrayList = new ArrayList<>();
    Context context;


    public adapter(List<Source> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Source object = arrayList.get(position);
        String myCategory = object.getCategory();
        if (myCategory.equals(MainActivity.category)) {
            holder.name.setText(object.getName());
            holder.category.setText(object.getUrl());
            holder.discription.setText(object.getDescription());

            holder.holderView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, headlines.class);
                    intent.putExtra("Source", object.getId());
                    context.startActivity(intent);
                }
            });
        }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView name, category, discription;
        View holderView;

        public MyViewHolder(View view) {
            super(view);

            holderView = view;
            discription = view.findViewById(R.id.description);
            cardView = view.findViewById(R.id.cv);
            name = (TextView) view.findViewById(R.id.name);
            category = (TextView) view.findViewById(R.id.category);
        }
    }
}