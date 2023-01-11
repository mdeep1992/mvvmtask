package com.example.mvvmtask.Model.CategoryItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("discount")
    @Expose
    private Integer discount;
    @SerializedName("gst_percent")
    @Expose
    private Integer gstPercent;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("thumbnail")
    @Expose
    private Object thumbnail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getGstPercent() {
        return gstPercent;
    }

    public void setGstPercent(Integer gstPercent) {
        this.gstPercent = gstPercent;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Object getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Object thumbnail) {
        this.thumbnail = thumbnail;
    }

}
