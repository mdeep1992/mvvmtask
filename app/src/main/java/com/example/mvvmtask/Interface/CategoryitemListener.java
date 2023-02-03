package com.example.mvvmtask.Interface;

import com.example.mvvmtask.Model.CategoryItemList.Item;

import java.util.ArrayList;

public interface CategoryitemListener {
    void OnClick(Item item, int position,Boolean isselect);
}
