package com.akozlowski.doomvision.pojo;

import com.google.gson.annotations.Expose;

/**
 * Created by akozlowski on 16/08/15.
 */
public class EchoTest {

    @Expose
    private String text;

    /**
     * @return The text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text The text
     */
    public void setText(String text) {
        this.text = text;
    }
}
