package com.andalus.abomed7at55.mn_edek_a7la.prefs

import android.content.Context
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.DUMB_PREFS_FILE

class PrefsManagerImpl(private val context: Context) : PrefsManager<Int, Boolean> {

    private var fileName = DUMB_PREFS_FILE

    private var prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)

    override fun save(key: Int, value: Boolean): Boolean {
        if (prefs.getBoolean("$key", false) != value) {
            prefs.edit().putBoolean("$key", value).apply()
            return true
        }
        return false
    }

    override fun load(): List<Int> {
        val ids = mutableListOf<Int>()
        prefs.all.forEach {
            if (it.value == true)
                ids.add(it.key.toInt())
        }
        return ids
    }

    override fun invert(key: Int): Boolean {
        prefs.edit().putBoolean("$key", !prefs.getBoolean("$key", false)).apply()
        return prefs.getBoolean("$key", false)
    }

    override fun get(key: Int): Boolean {
        return prefs.getBoolean("$key", false)
    }

    override fun setPrefsFile(fileName: String) {
        if (fileName != this.fileName) {
            prefs = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
            this.fileName = fileName
        }
    }

}