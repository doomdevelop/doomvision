package com.akozlowski.doomvision.pojo;

import com.google.gson.annotations.Expose;

/**
 * Created by akozlowski on 14/08/15.
 */
public class Contributor {
    @Expose
    private String id;

    /**
     * @return The id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id) {
        this.id = id;
    }
}
