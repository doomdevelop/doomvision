package com.akozlowski.doomvision.manager;

import com.akozlowski.doomvision.pojo.Response;

import javax.inject.Singleton;

/**
 * Created by akozlowski on 17/08/15.
 */
@Singleton
public class DataManager {
    private static DataManager instance;

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null)
            throw (new IllegalStateException("BrushingStateManager not initialized"));
        return (instance);
    }

    public static DataManager createInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new DataManager();
        return instance;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }

    private Response response;
}
