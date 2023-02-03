package com.example.mvvmtask.Model.CategoryItemList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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
    private Boolean select=false;
    private Double total_amt= 0.0;
    private int selected_position;
    private  int qty ;
    private Double qtys ;

    public Double getTotal_amt() {
        return total_amt;
    }

    public void setTotal_amt(Double total_amt) {
        this.total_amt = total_amt;
    }

    public int getSelected_position() {
        return selected_position;
    }

    public void setSelected_position(int selected_position) {
        this.selected_position = selected_position;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Double getQtys() {
        return qtys;
    }

    public void setQtys(Double qtys) {
        this.qtys = qtys;
    }

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }

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
