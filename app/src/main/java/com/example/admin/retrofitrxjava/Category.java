package com.example.admin.retrofitrxjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

public class Category extends AppCompatActivity {

    ImageView general, sports, business, entertainment, health, science, technology;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        general = findViewById(R.id.generalNews);
        entertainment = findViewById(R.id.entertainmentNews);
        sports = findViewById(R.id.sportsNews);
        technology = findViewById(R.id.techNews);
        health = findViewById(R.id.healthNews);
        science = findViewById(R.id.scienceNews);
        business = findViewById(R.id.businessNews);

        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Category.this, MainActivity.class);
                intent.putExtra("Category", "general");
                startActivity(intent);
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, MainActivity.class);
                intent.putExtra("Category", "entertainment");
                startActivity(intent);
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, MainActivity.class);
                intent.putExtra("Category", "business");
                startActivity(intent);
            }
        });
        technology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, MainActivity.class);
                intent.putExtra("Category", "technology");
                startActivity(intent);
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, MainActivity.class);
                intent.putExtra("Category", "science");
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, MainActivity.class);
                intent.putExtra("Category", "sports");
                startActivity(intent);
            }
        });
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Category.this, MainActivity.class);
                intent.putExtra("Category", "health");
                startActivity(intent);
            }
        });
    }
}
