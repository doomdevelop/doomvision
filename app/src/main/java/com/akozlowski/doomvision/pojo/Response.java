package com.akozlowski.doomvision.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akozlowski on 14/08/15.
 */
public class Response {

    @Expose
    private int page;
    @SerializedName("per_page")
    @Expose
    private int perPage;
    @SerializedName("total_count")
    @Expose
    private int totalCount;
    @SerializedName("search_id")
    @Expose
    private String searchId;
    @Expose
    private List<Data> data = new ArrayList<Data>();

    /**
     * @return The page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page The page
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return The perPage
     */
    public int getPerPage() {
        return perPage;
    }

    /**
     * @param perPage The per_page
     */
    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    /**
     * @return The totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount The total_count
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return The searchId
     */
    public String getSearchId() {
        return searchId;
    }

    /**
     * @param searchId The search_id
     */
    public void setSearchId(String searchId) {
        this.searchId = searchId;
    }

    /**
     * @return The data
     */
    public List<Data> getData() {
        return data;
    }

    /**
     * @param data The data
     */
    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Assets {

    }
}
