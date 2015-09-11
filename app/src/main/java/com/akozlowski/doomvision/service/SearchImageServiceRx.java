package com.akozlowski.doomvision.service;

import com.akozlowski.doomvision.pojo.Response;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by and on 10.09.15.
 */
public interface SearchImageServiceRx {
    @GET("/images/search")
    Observable<Response> search(@Query("query") String query, @Query("view") String view);
}
