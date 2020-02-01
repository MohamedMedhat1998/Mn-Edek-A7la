package com.andalus.abomed7at55.mn_edek_a7la.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

object Measurements {
    /**
     * This method is used to calculate the number of columns in the RecyclerView GridLayoutManager
     * dynamically
     * @return the suitable number of columns
     */
    @JvmStatic
    fun numberOfGridLayoutColumns(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        val widthDivider = 400
        val width = displayMetrics.widthPixels
        val nColumns = width / widthDivider
        return if (nColumns < 2) 2 else nColumns
    }
}