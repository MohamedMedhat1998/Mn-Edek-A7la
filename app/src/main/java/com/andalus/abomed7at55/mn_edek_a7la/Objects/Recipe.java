package com.andalus.abomed7at55.mn_edek_a7la.Objects;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Recipe {
    @PrimaryKey
    private int mId;
    private String mTitle;
    private String mIngredients;
    private String mSteps;
    private String mCategory;
    private String mPhotoLink;
    private String mVideoLink;

    public Recipe(int id, String title, String ingredients, String steps, String category, String photoLink, String videoLink) {
        mId = id;
        mTitle = title;
        mIngredients = ingredients;
        mSteps = steps;
        mCategory = category;
        mPhotoLink = photoLink;
        mVideoLink = videoLink;
    }

    public int getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getIngredients() {
        return mIngredients;
    }

    public String getSteps() {
        return mSteps;
    }

    public String getCategory() {
        return mCategory;
    }

    public String getPhotoLink() {
        return mPhotoLink;
    }

    public String getVideoLink() {
        return mVideoLink;
    }
}
