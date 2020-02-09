package com.andalus.abomed7at55.mn_edek_a7la.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

@Database(entities = [Recipe::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val dao: RecipeDao

    companion object {
        //TODO update this value when you push new records to the local database before releasing update
        const val NEW_VERSION = 0
        var currentVersion = 0
    }
}