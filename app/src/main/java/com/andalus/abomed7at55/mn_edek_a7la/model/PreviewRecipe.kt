package com.andalus.abomed7at55.mn_edek_a7la.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.COLUMN_CATEGORY
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.COLUMN_ID
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.COLUMN_IS_FAVORITE
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.COLUMN_PHOTO_LINK
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.COLUMN_TITLE

data class PreviewRecipe(@ColumnInfo(name = COLUMN_ID) @PrimaryKey val id: Int,
                         @ColumnInfo(name = COLUMN_TITLE) val title: String,
                         @ColumnInfo(name = COLUMN_CATEGORY) val category: String,
                         @ColumnInfo(name = COLUMN_PHOTO_LINK) val photoLink: String,
                         @ColumnInfo(name = COLUMN_IS_FAVORITE) val isFavorite: Boolean)
