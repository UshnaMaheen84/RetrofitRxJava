package com.example.admin.retrofitrxjava;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.admin.retrofitrxjava.models.News;
import com.example.admin.retrofitrxjava.retrofit.MyApi;
import com.example.admin.retrofitrxjava.retrofit.RetrofitBuilder;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class Splash extends AppCompatActivity {

    CompositeDisposable disposable = new CompositeDisposable();
    MyApi my_api;
    ProgressDialog progressDialog;
    LinearLayout noNetworkLayout, main_layout;
    Button retry_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Retrofit retrofit = RetrofitBuilder.getInstance();
        my_api = retrofit.create(MyApi.class);


        main_layout = findViewById(R.id.NetworkLayout);
        noNetworkLayout = findViewById(R.id.noNetworkLayout);
        retry_btn = findViewById(R.id.retry);

        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(haveNetwork()){
                    main_layout.setVisibility(View.VISIBLE);
                    noNetworkLayout.setVisibility(View.GONE);
                    progressDialog.setMessage("Getting news");
                    progressDialog.setTitle("Please wait..");
                    progressDialog.show();

                    Thread myThread = new Thread() {
                        @Override
                        public void run() {
                            try {

                                sleep(1000);
                                try {
                                    getData();
                                } catch (Exception e) {
                                    Log.e("ExceptionFromServer", e.getMessage());
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    };
                    myThread.start();

                }
                else{
                    Toast.makeText(Splash.this, " Please get Online first. ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (haveNetwork()) {
            //Connected to the internet
            main_layout.setVisibility(View.VISIBLE);
            noNetworkLayout.setVisibility(View.GONE);

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Getting news");
            progressDialog.setTitle("Please wait..");
            progressDialog.show();

            Thread myThread = new Thread() {
                @Override
                public void run() {
                    try {

                        sleep(1000);
                        try {
                            getData();
                        } catch (Exception e) {
                            Log.e("ExceptionFromServer", e.getMessage());
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
        } else {
            main_layout.setVisibility(View.GONE);
            noNetworkLayout.setVisibility(View.VISIBLE);
        }


    }
    private void getData() {

        disposable.add(my_api.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<News>() {
                    @Override
                    public void accept(News news) throws Exception {

                        progressDialog.dismiss();
                        common.sources = news.getSources();
                        Intent intent = new Intent(Splash.this, Category.class);
                        startActivity(intent);
                        finish();
                    }
                }));

    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
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
