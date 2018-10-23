package com.example.admin.retrofitrxjava;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
    ArrayList <Article> articles= new ArrayList<>();
    RecyclerView rview;
    ProgressDialog progressDialog;
    headlineAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_headlines);


        Retrofit retrofit = RetrofitBuilder.getInstance();
        my_api = retrofit.create(MyApi.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Getting news");
        progressDialog.setTitle("Please wait..");
        progressDialog.show();
        try {
            getData();
        } catch (Exception e) {
            Log.e("ExceptionFromServer", e.getMessage());
        }

        rview = findViewById(R.id.rview);
        }



        private void getData() {
            String sourceId= getIntent().getExtras().getString("Source");
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
            progressDialog.dismiss();
        }
}
