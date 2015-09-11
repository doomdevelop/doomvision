package com.akozlowski.doomvision.service;

import com.akozlowski.doomvision.pojo.Response;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by akozlowski on 14/08/15.
 * https://api.shutterstock.com/v2/images/search?license=commercial&&&&&&&&&&query=dog&&sort=popular&view=minimal
 */
public interface SearchImageService {


    @GET("/images/search")
    void search(@Query("query") String query, @Query("view") String view, Callback<Response> cb);

//    @GET("/search")
//    void search(@Query("license") String license, @Query("query") String query, Callback<Response> cb);

//    @GET("/search?license=commercial&&&&&&&&&&query={query}")
//    void search(@Query("query") String query, Callback<Response> cb);
}
