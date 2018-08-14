package com.andalus.abomed7at55.mn_edek_a7la.Utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

public class Measurements {

    private Measurements(){

    }

    /**
     * This method is used to calculate the number of columns in the RecyclerView GridLayoutManager
     * dynamically
     * @return the suitable number of columns
     */
    public static int numberOfGridLayoutColumns(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 400;
        int width = displayMetrics.widthPixels;
        int nColumns = width / widthDivider;
        if (nColumns < 2) return 2;
        return nColumns;
    }

}
