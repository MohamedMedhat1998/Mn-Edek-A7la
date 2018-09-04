package com.andalus.abomed7at55.mn_edek_a7la.Data;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = Recipe.class,version = 1 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{

    public static final String DATABASE_NAME = "Food.db";
    public static final String VERSION_KEY = "version_key";

    private static AppDatabase mAppDatabase;

    //TODO update this value when you push new records to the local database before releasing update
    private static final int NEW_VERSION = 9;

    private static int mCurrentVersion;

    public static AppDatabase getInstance(Context context){
        if(mAppDatabase == null){
            mAppDatabase = Room.databaseBuilder(context,AppDatabase.class,DATABASE_NAME)
                    .build();
        }
        return mAppDatabase;
    }

    public static void copyDatabase(Context context) throws IOException {
        final File outputFile = context.getDatabasePath(DATABASE_NAME);
        Log.d("NEW VERSION" , NEW_VERSION + "");
        Log.d("CURRENT VERSION" , mCurrentVersion+"");
        if(mCurrentVersion < NEW_VERSION){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(VERSION_KEY,NEW_VERSION);
            editor.apply();
            boolean isDeleted = outputFile.delete();
            boolean isDataDeleted = context.deleteDatabase(DATABASE_NAME);
            if(isDeleted) Log.d("FILE","FILE DELETED");
            if(isDataDeleted) Log.d("FILE","DATABASE DELETED");
        }

        if(!outputFile.exists()){
            InputStream inputStream = context.getAssets().open(DATABASE_NAME);
            File file = context.getFilesDir();
            File databaseFolder = new File(file.getParent(),"databases");
            databaseFolder.mkdirs();
            File databaseFile = new File(databaseFolder.getAbsolutePath(),DATABASE_NAME);
            OutputStream outputStream = new FileOutputStream(databaseFile);

            byte[] buffer = new byte[1024];
            int bufferSize;

            while ((bufferSize = inputStream.read(buffer)) > 0){
                outputStream.write(buffer,0,bufferSize);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
            Log.d("File","DATABASE COPIED");
        }
    }

    public static void setCurrentVersion(int currentVersion){
        mCurrentVersion = currentVersion;
    }

    public abstract RecipeDao getRecipeDao();

}
