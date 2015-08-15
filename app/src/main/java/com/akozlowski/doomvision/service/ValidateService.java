package com.akozlowski.doomvision.service;

import com.akozlowski.doomvision.pojo.Response;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by akozlowski on 15/08/15.
 */
public interface ValidateService {
    @GET("/test/validate")
    void validate(@Query("id") String id, @Query("tag1") String tag1, @Query("tag2") String tag2, Callback<Response> cb);
}
