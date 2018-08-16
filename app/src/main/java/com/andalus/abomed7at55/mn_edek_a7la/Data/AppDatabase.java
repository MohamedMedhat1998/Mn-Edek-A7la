package com.andalus.abomed7at55.mn_edek_a7la.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {Recipe.class},version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "Food.db";

    private static AppDatabase mAppDatabase;

    public static AppDatabase getInstance(Context context){
        if(mAppDatabase == null){
            mAppDatabase = Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME).build();
        }
        return mAppDatabase;
    }

    public static void copyDatabase(Context context) throws IOException {
        final File outputFile = context.getDatabasePath(DATABASE_NAME);
        if(!outputFile.exists()){
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            OutputStream outputStream = new FileOutputStream(context.getDatabasePath(DATABASE_NAME));

            byte[] buffer = new byte[1024];
            int bufferSize;

            while ((bufferSize = inputStream.read(buffer)) > 0){
                outputStream.write(buffer,0,bufferSize);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        }
    }

    public abstract RecipeDao getRecipeDao();
}
