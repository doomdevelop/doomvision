package com.akozlowski.doomvision.service;

import com.akozlowski.doomvision.pojo.Response;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by akozlowski on 14/08/15.
 */
public interface SearchImageService {


    @GET("/images/search")
    void search(@Query("query") String query, @Query("view") String view, Callback<Response> cb);

}
