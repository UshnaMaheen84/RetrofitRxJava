package com.example.admin.retrofitrxjava.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.retrofitrxjava.R;
import com.example.admin.retrofitrxjava.headlines;
import com.example.admin.retrofitrxjava.models.IconBetterIdea;
import com.example.admin.retrofitrxjava.models.Source;
import com.example.admin.retrofitrxjava.retrofit.IconBetterIdeaClient;
import com.example.admin.retrofitrxjava.retrofit.MyApi;
import com.example.admin.retrofitrxjava.retrofit.RetrofitBuilder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class adapter extends RecyclerView.Adapter<adapter.MyViewHolder> {
    List<Source> arrayList = new ArrayList<>();
    Context context;
    MyApi my_api;
    CompositeDisposable disposable = new CompositeDisposable();


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

//        Retrofit retrofit = IconBetterIdeaClient.getClient();
//        my_api = retrofit.create(MyApi.class);
//
//        disposable.add(my_api.getIconUrl()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<IconBetterIdea>() {
//                    @Override
//                    public void accept(IconBetterIdea iconBetterIdea) throws Exception {
//
//                    }
//
//                }));

        Picasso.get()
                .load( "https://icons.better-idea.org/icon?url="+object.getUrl()+"&size=80..120..200")
                .into(holder.imageView);


        holder.name.setText(object.getName());
        holder.category.setText(object.getCategory());


        

        holder.holderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, headlines.class);
                intent.putExtra("Source", object.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, discription;
        View holderView;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);

            holderView = view;
            imageView = view.findViewById(R.id.image);
            name = (TextView) view.findViewById(R.id.name);
            category = (TextView) view.findViewById(R.id.category);
        }
    }
}