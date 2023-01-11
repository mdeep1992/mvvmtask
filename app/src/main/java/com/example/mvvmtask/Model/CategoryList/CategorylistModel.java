package com.example.mvvmtask.Model.CategoryList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategorylistModel {
    @SerializedName("category")
    @Expose
    private List<Category> category = null;

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
}
