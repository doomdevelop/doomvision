package com.akozlowski.doomvision.manager;

import com.akozlowski.doomvision.pojo.Response;

import javax.inject.Singleton;

/**
 * Created by akozlowski on 17/08/15.
 */
@Singleton
public class DataManager {
    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    private Response response;
}
