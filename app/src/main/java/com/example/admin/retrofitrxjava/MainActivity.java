package com.example.admin.retrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.retrofitrxjava.adapters.adapter;
import com.example.admin.retrofitrxjava.models.Source;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView rview;

    public static String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        category = getIntent().getExtras().getString("Category");

        rview = findViewById(R.id.rview);
        rview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter adpt = new adapter(filterData(), MainActivity.this);
        rview.setAdapter(adpt);
        adpt.notifyDataSetChanged();

    }

    public List<Source> filterData(){

        ArrayList<Source> generalArrayList = new ArrayList<>();
        for (int i =0; i<common.sources.size(); i++){
            if(common.sources.get(i).getCategory().equals(category))
                generalArrayList.add(common.sources.get(i));

        }
        return generalArrayList;
    }




}

