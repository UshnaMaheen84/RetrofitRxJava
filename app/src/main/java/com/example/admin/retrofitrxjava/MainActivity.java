package com.example.admin.retrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.retrofitrxjava.adapters.adapter;

public class MainActivity extends AppCompatActivity {


    RecyclerView rview;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rview = findViewById(R.id.rview);
        rview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter adpt = new adapter(common.sources, MainActivity.this);
        rview.setAdapter(adpt);


}
}

