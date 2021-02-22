package com.example.admin.retrofitrxjava;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.retrofitrxjava.adapters.adapter;
import com.example.admin.retrofitrxjava.models.Source;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rview;
    LinearLayout noNetworkLayout, main_layout;
    Button retry_btn;
    public static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category = getIntent().getExtras().getString("Category");

        main_layout = findViewById(R.id.NetworkLayout);
        noNetworkLayout = findViewById(R.id.noNetworkLayout);
        retry_btn = findViewById(R.id.retry);

        rview = findViewById(R.id.rview);
        rview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        if (haveNetwork()) {
            main_layout.setVisibility(View.VISIBLE);
            noNetworkLayout.setVisibility(View.GONE);
            adapter adpt = new adapter(filterData(), MainActivity.this);
            rview.setAdapter(adpt);
            adpt.notifyDataSetChanged();

        } else {
            main_layout.setVisibility(View.GONE);
            noNetworkLayout.setVisibility(View.VISIBLE);
        }
        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (haveNetwork()) {
                    main_layout.setVisibility(View.VISIBLE);
                    noNetworkLayout.setVisibility(View.GONE);
                    adapter adpt = new adapter(filterData(), MainActivity.this);
                    rview.setAdapter(adpt);
                    adpt.notifyDataSetChanged();

                } else {
                    Toast.makeText(MainActivity.this, " Please get Online first. ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public List<Source> filterData(){

        //here call filter data
        //jo common.sources mai sai un objects ko filter kary gi jin ki category general hai
        //then usko adapter mai bhejain

        ArrayList<Source> generalArrayList = new ArrayList<>();
        for (int i =0; i<common.sources.size(); i++){
            if(common.sources.get(i).getCategory().equals(category))
                generalArrayList.add(common.sources.get(i));
        }
        return generalArrayList;
    }


    public boolean haveNetwork() {

        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }


}

