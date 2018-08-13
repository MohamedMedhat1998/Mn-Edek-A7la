package com.andalus.abomed7at55.mn_edek_a7la.Objects;

public class FoodCategory {

    private String mTitle;
    private int mIconId;

    public FoodCategory(String title, int id){
        mTitle = title;
        mIconId = id;
    }

    public String getTitle(){
        return mTitle;
    }

    public int getIconId(){
        return mIconId;
    }

}
