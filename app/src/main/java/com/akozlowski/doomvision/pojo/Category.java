package com.akozlowski.doomvision.pojo;

import com.google.gson.annotations.Expose;

/**
 * Created by akozlowski on 14/08/15.
 */
public class Category {
    @Expose
    private String id;
    @Expose
    private String name;

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

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
