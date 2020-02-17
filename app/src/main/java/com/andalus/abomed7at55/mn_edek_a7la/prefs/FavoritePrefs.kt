package com.andalus.abomed7at55.mn_edek_a7la.prefs

import android.content.Context
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.FAVORITE_PREFS_FILE_NAME

class FavoritePrefs(context: Context) : PrefsManager<Int, Boolean> {

    private val prefs = context.getSharedPreferences(FAVORITE_PREFS_FILE_NAME, Context.MODE_PRIVATE)

    override fun save(key: Int, value: Boolean): Boolean {
        if (prefs.getBoolean("$key", false) != value){
            prefs.edit().putBoolean("$key", value).apply()
            return true
        }
        return false
    }

    override fun load(): List<Int> {
        val favoriteIds = mutableListOf<Int>()
        prefs.all.forEach {
            if (it.value == true)
                favoriteIds.add(it.key.toInt())
        }
        return favoriteIds
    }

}