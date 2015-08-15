package com.akozlowski.doomvision.pojo;

import com.google.gson.annotations.Expose;

/**
 * Created by akozlowski on 14/08/15.
 */
public class SmallThumb {
    @Expose
    private int height;
    @Expose
    private String url;
    @Expose
    private int width;

    /**
     * @return The height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(int width) {
        this.width = width;
    }
}
