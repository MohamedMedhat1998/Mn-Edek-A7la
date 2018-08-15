package com.andalus.abomed7at55.mn_edek_a7la.Objects;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Recipe {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_INGREDIENTS = "ingredients";
    public static final String COLUMN_STEPS = "steps";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_PHOTO_LINK = "photo_link";
    public static final String COLUMN_VIDEO_LINK = "video_link";

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_ID)
    private int mId;
    @ColumnInfo(name = COLUMN_TITLE)
    private String mTitle;
    @ColumnInfo(name = COLUMN_INGREDIENTS)
    private String mIngredients;
    @ColumnInfo(name = COLUMN_STEPS)
    private String mSteps;
    @ColumnInfo(name = COLUMN_CATEGORY)
    private String mCategory;
    @ColumnInfo(name = COLUMN_PHOTO_LINK)
    private String mPhotoLink;
    @ColumnInfo(name = COLUMN_VIDEO_LINK)
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
