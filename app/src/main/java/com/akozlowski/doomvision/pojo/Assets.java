package com.akozlowski.doomvision.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by akozlowski on 14/08/15.
 */
public class Assets {

    @SerializedName("huge_jpg")
    @Expose
    private HugeJpg hugeJpg;
    @SerializedName("medium_jpg")
    @Expose
    private MediumJpg mediumJpg;
    @SerializedName("huge_tiff")
    @Expose
    private HugeTiff hugeTiff;
    @SerializedName("supersize_jpg")
    @Expose
    private SupersizeJpg supersizeJpg;
    @SerializedName("supersize_tiff")
    @Expose
    private SupersizeTiff supersizeTiff;
    @SerializedName("small_jpg")
    @Expose
    private SmallJpg smallJpg;
    @Expose
    private Preview preview;
    @SerializedName("small_thumb")
    @Expose
    private SmallThumb smallThumb;
    @SerializedName("large_thumb")
    @Expose
    private LargeThumb largeThumb;

    /**
     * @return The hugeJpg
     */
    public HugeJpg getHugeJpg() {
        return hugeJpg;
    }

    /**
     * @param hugeJpg The huge_jpg
     */
    public void setHugeJpg(HugeJpg hugeJpg) {
        this.hugeJpg = hugeJpg;
    }

    /**
     * @return The mediumJpg
     */
    public MediumJpg getMediumJpg() {
        return mediumJpg;
    }

    /**
     * @param mediumJpg The medium_jpg
     */
    public void setMediumJpg(MediumJpg mediumJpg) {
        this.mediumJpg = mediumJpg;
    }

    /**
     * @return The hugeTiff
     */
    public HugeTiff getHugeTiff() {
        return hugeTiff;
    }

    /**
     * @param hugeTiff The huge_tiff
     */
    public void setHugeTiff(HugeTiff hugeTiff) {
        this.hugeTiff = hugeTiff;
    }

    /**
     * @return The supersizeJpg
     */
    public SupersizeJpg getSupersizeJpg() {
        return supersizeJpg;
    }

    /**
     * @param supersizeJpg The supersize_jpg
     */
    public void setSupersizeJpg(SupersizeJpg supersizeJpg) {
        this.supersizeJpg = supersizeJpg;
    }

    /**
     * @return The supersizeTiff
     */
    public SupersizeTiff getSupersizeTiff() {
        return supersizeTiff;
    }

    /**
     * @param supersizeTiff The supersize_tiff
     */
    public void setSupersizeTiff(SupersizeTiff supersizeTiff) {
        this.supersizeTiff = supersizeTiff;
    }

    /**
     * @return The smallJpg
     */
    public SmallJpg getSmallJpg() {
        return smallJpg;
    }

    /**
     * @param smallJpg The small_jpg
     */
    public void setSmallJpg(SmallJpg smallJpg) {
        this.smallJpg = smallJpg;
    }

    /**
     * @return The preview
     */
    public Preview getPreview() {
        return preview;
    }

    /**
     * @param preview The preview
     */
    public void setPreview(Preview preview) {
        this.preview = preview;
    }

    /**
     * @return The smallThumb
     */
    public SmallThumb getSmallThumb() {
        return smallThumb;
    }

    /**
     * @param smallThumb The small_thumb
     */
    public void setSmallThumb(SmallThumb smallThumb) {
        this.smallThumb = smallThumb;
    }

    /**
     * @return The largeThumb
     */
    public LargeThumb getLargeThumb() {
        return largeThumb;
    }

    /**
     * @param largeThumb The large_thumb
     */
    public void setLargeThumb(LargeThumb largeThumb) {
        this.largeThumb = largeThumb;
    }

}
