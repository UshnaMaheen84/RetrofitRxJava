package com.example.admin.retrofitrxjava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

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

//    ProgressDialog progressSetter() {
//
//
//        return progressDialog;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Retrofit retrofit = RetrofitBuilder.getInstance();
        my_api = retrofit.create(MyApi.class);
//
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
                        Intent intent = new Intent(Splash.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }
                }));
        progressDialog.dismiss();


    }

    @Override
    protected void onStop() {
        super.onStop();
        disposable.dispose();
    }

}
