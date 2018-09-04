package com.andalus.abomed7at55.mn_edek_a7la.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.FavoriteRecipe;

@Database(entities = FavoriteRecipe.class,version = 1,exportSchema = false)
public abstract class FavoriteDatabase extends RoomDatabase{

    public static final String FAVORITE_DATABASE_NAME = "favorite.db";

    private static FavoriteDatabase mFavoriteDatabase;

    public static FavoriteDatabase getInstance(Context context){
        if(mFavoriteDatabase == null){
            mFavoriteDatabase = Room.databaseBuilder(context,FavoriteDatabase.class,FAVORITE_DATABASE_NAME).build();
        }
        return mFavoriteDatabase;
    }

    public abstract FavoriteRecipeDao getFavoriteRecipeDao();
}
