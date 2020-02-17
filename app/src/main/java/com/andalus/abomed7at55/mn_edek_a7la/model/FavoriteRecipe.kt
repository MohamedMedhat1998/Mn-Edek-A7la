package com.andalus.abomed7at55.mn_edek_a7la.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

@Entity
data class FavoriteRecipe(@ColumnInfo(name = Constants.COLUMN_ID) @PrimaryKey val id: Int,
                          @ColumnInfo(name = Constants.COLUMN_TITLE) val title: String,
                          @ColumnInfo(name = Constants.COLUMN_INGREDIENTS) val ingredients: String,
                          @ColumnInfo(name = Constants.COLUMN_STEPS) val steps: String,
                          @ColumnInfo(name = Constants.COLUMN_CATEGORY) val category: String,
                          @ColumnInfo(name = Constants.COLUMN_PHOTO_LINK) val photoLink: String,
                          @ColumnInfo(name = Constants.COLUMN_VIDEO_LINK) val videoLink: String)