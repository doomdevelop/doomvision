package com.akozlowski.doomvision.service;

import com.akozlowski.doomvision.pojo.EchoTest;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by akozlowski on 15/08/15.
 */
public interface TestService {
    @GET("/test")
    EchoTest test(@Query("test") String testText);
}
