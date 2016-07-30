package com.example.ohdok.classkitapp.dao;

import java.util.ArrayList;

/**
 * Created by ohdok on 2016-07-31.
 */
public class FoodData {
    private String foodName;
    private ArrayList<Integer> imageList;

    public ArrayList<Integer> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Integer> imageList) {
        this.imageList = imageList;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
