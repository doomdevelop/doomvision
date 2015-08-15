package com.akozlowski.doomvision.service;

import com.akozlowski.doomvision.pojo.Response;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by akozlowski on 15/08/15.
 */
public interface TestService {
    @GET("/test")
    void test(Callback<Response> cb);
}
