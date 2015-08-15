package com.akozlowski.doomvision.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akozlowski on 14/08/15.
 */
public class HugeJpg {
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @Expose
    private int dpi;
    @SerializedName("file_size")
    @Expose
    private int fileSize;
    @Expose
    private String format;
    @Expose
    private int height;
    @SerializedName("is_licensable")
    @Expose
    private boolean isLicensable;
    @Expose
    private int width;

    /**
     * @return The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * @param displayName The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return The dpi
     */
    public int getDpi() {
        return dpi;
    }

    /**
     * @param dpi The dpi
     */
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    /**
     * @return The fileSize
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize The file_size
     */
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * @param format The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

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
     * @return The isLicensable
     */
    public boolean isIsLicensable() {
        return isLicensable;
    }

    /**
     * @param isLicensable The is_licensable
     */
    public void setIsLicensable(boolean isLicensable) {
        this.isLicensable = isLicensable;
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
