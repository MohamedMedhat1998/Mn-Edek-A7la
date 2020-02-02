package com.andalus.abomed7at55.mn_edek_a7la.data;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class FavoriteDatabase extends RoomDatabase {

    public static final String FAVORITE_DATABASE_NAME = "favorite.db";

    private static FavoriteDatabase mFavoriteDatabase;

    public static FavoriteDatabase getInstance(Context context) {
        if (mFavoriteDatabase == null) {
            mFavoriteDatabase = Room.databaseBuilder(context, FavoriteDatabase.class, FAVORITE_DATABASE_NAME).build();
        }
        return mFavoriteDatabase;
    }

    public abstract FavoriteRecipeDao getFavoriteRecipeDao();
}
