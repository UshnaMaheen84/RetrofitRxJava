package com.example.admin.retrofitrxjava.retrofit;

import com.example.admin.retrofitrxjava.models.IconBetterIdea;
import com.example.admin.retrofitrxjava.models.News;
import com.example.admin.retrofitrxjava.models.headlineModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface MyApi {

    @GET ("sources?apiKey=632040e6ff184ac19da403aa919a57f9")
    Observable<News> getNews();


    @GET("top-headlines")
    Observable<headlineModel> getHeadline(@Query("sources") String source,
                                          @Query("apiKey") String apiKey);
//
//    @GET("icon?url=")
//    Observable<IconBetterIdea> getIconUrl();

}