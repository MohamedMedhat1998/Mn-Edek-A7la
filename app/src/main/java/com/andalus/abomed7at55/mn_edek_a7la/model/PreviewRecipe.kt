package com.andalus.abomed7at55.mn_edek_a7la.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

data class PreviewRecipe(@ColumnInfo(name = Constants.COLUMN_ID) @PrimaryKey val id: Int,
                         @ColumnInfo(name = Constants.COLUMN_TITLE) val title: String,
                         @ColumnInfo(name = Constants.COLUMN_CATEGORY) val category: String,
                         @ColumnInfo(name = Constants.COLUMN_PHOTO_LINK) val photoLink: String)