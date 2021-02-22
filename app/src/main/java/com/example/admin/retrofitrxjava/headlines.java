package com.example.admin.retrofitrxjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.retrofitrxjava.adapters.headlineAdapter;
import com.example.admin.retrofitrxjava.models.Article;
import com.example.admin.retrofitrxjava.models.News;
import com.example.admin.retrofitrxjava.models.headlineModel;
import com.example.admin.retrofitrxjava.retrofit.MyApi;
import com.example.admin.retrofitrxjava.retrofit.RetrofitBuilder;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class headlines extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();
    MyApi my_api;
    ArrayList<Article> articles = new ArrayList<>();
    RecyclerView rview;
    ProgressDialog progressDialog;
    headlineAdapter adapter;
    LinearLayout noNetworkLayout, main_layout;
    Button retry_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);

        noNetworkLayout = findViewById(R.id.noNetworkLayout);
        retry_btn = findViewById(R.id.retry);
        rview = findViewById(R.id.rview);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        my_api = retrofit.create(MyApi.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting news");
        progressDialog.setTitle("Please wait..");


        if (haveNetwork()) {
            //Connected to the internet
            main_layout.setVisibility(View.VISIBLE);
            noNetworkLayout.setVisibility(View.GONE);

            progressDialog.show();
            try {
                getData();
            } catch (Exception e) {
                Log.e("ExceptionFromServer", e.getMessage());
            }

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
                    progressDialog.setMessage("Getting news");
                    progressDialog.setTitle("Please wait..");
                    progressDialog.show();
                    try {
                        getData();
                    } catch (Exception e) {
                        Log.e("ExceptionFromServer", e.getMessage());
                    }
                } else {
                    Toast.makeText(headlines.this, " Please get Online first. ", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void getData() {
        String sourceId = getIntent().getExtras().getString("Source");
        disposable.add(my_api.getHeadline(sourceId, getString(R.string.api_key))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<headlineModel>() {
                    @Override
                    public void accept(headlineModel headline) throws Exception {

                        progressDialog.dismiss();

                        rview.setLayoutManager(new LinearLayoutManager(headlines.this));
                        adapter = new headlineAdapter(headline.getArticles(), headlines.this);
                        rview.setAdapter(adapter);

                    }
                }));
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
