package com.andalus.abomed7at55.mn_edek_a7la.Objects;

public class FoodCategory {

    public static final String MEAT_TAG = "meat";
    public static final String FISH_TAG = "fish";
    public static final String SWEET_TAG = "sweet";
    public static final String DRINK_TAG = "drink";
    public static final String STARCHES_TAG = "starches";
    public static final String APPETIZER_TAG = "appetizer";
    public static final String IDEA_TAG = "idea";
    public static final String DIET_TAG = "diet";
    public static final String BAKERY_TAG = "bakery";
    public static final String MILK_TAG = "milk";

    public static final String TAG_KEY = "food_tag";

    private String mTitle;
    private int mIconId;
    private String mTag;

    public FoodCategory(String title, int id, String tag){
        mTitle = title;
        mIconId = id;
        mTag = tag;
    }

    public String getTitle(){
        return mTitle;
    }

    public int getIconId(){
        return mIconId;
    }

    public String getTag(){
        return mTag;
    }

}
