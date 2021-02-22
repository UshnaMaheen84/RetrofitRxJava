package com.example.admin.retrofitrxjava;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Category extends AppCompatActivity {

    CardView general, sports, business, entertainment, health, technology;
    LinearLayout noNetworkLayout, main_layout;
    Button retry_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        general = findViewById(R.id.generalNews);
        entertainment = findViewById(R.id.entertainmentNews);
        sports = findViewById(R.id.sportsNews);
        technology = findViewById(R.id.techNews);
        health = findViewById(R.id.healthNews);
        business = findViewById(R.id.businessNews);
        main_layout = findViewById(R.id.NetworkLayout);
        noNetworkLayout = findViewById(R.id.noNetworkLayout);
        retry_btn = findViewById(R.id.retry);
        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (haveNetwork()) {
                    main_layout.setVisibility(View.VISIBLE);
                    noNetworkLayout.setVisibility(View.GONE);

                } else {
                    Toast.makeText(Category.this, " Please get Online first. ", Toast.LENGTH_SHORT).show();
                }
            }
        });
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (haveNetwork()) {
                    Intent intent = new Intent(Category.this, MainActivity.class);
                    intent.putExtra("Category", "general");
                    startActivity(intent);
                } else {
                    main_layout.setVisibility(View.GONE);
                    noNetworkLayout.setVisibility(View.VISIBLE);
                }

            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (haveNetwork()) {
                    Intent intent = new Intent(Category.this, MainActivity.class);
                    intent.putExtra("Category", "entertainment");
                    startActivity(intent);
                } else {
                    main_layout.setVisibility(View.GONE);
                    noNetworkLayout.setVisibility(View.VISIBLE);
                }

            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (haveNetwork()) {
                    Intent intent = new Intent(Category.this, MainActivity.class);
                    intent.putExtra("Category", "business");
                    startActivity(intent);
                } else {
                    main_layout.setVisibility(View.GONE);
                    noNetworkLayout.setVisibility(View.VISIBLE);
                }

            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (haveNetwork()) {
                    Intent intent = new Intent(Category.this, MainActivity.class);
                    intent.putExtra("Category", "technology");
                    startActivity(intent);
                } else {
                    main_layout.setVisibility(View.GONE);
                    noNetworkLayout.setVisibility(View.VISIBLE);
                }

            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (haveNetwork()) {
                    Intent intent = new Intent(Category.this, MainActivity.class);
                    intent.putExtra("Category", "sports");
                    startActivity(intent);
                } else {
                    main_layout.setVisibility(View.GONE);
                    noNetworkLayout.setVisibility(View.VISIBLE);
                }
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (haveNetwork()) {
                    Intent intent = new Intent(Category.this, MainActivity.class);
                    intent.putExtra("Category", "health");
                    startActivity(intent);
                } else {
                    main_layout.setVisibility(View.GONE);
                    noNetworkLayout.setVisibility(View.VISIBLE);
                }
            }
        });
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
