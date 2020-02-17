package com.andalus.abomed7at55.mn_edek_a7la.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andalus.abomed7at55.mn_edek_a7la.model.FavoriteRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe

@Dao
interface FavoriteDao {

    @Insert
    suspend fun addToFavorite(list: List<FavoriteRecipe>)

    @Insert
    suspend fun addToFavorite(favoriteRecipe: FavoriteRecipe)

    @Query("DELETE FROM FavoriteRecipe WHERE id = :id")
    suspend fun deleteFromFavorite(id: Int)

    @Query("DELETE FROM FavoriteRecipe WHERE id in (:ids)")
    suspend fun deleteFromFavorite(ids: List<Int>)

    @Query("SELECT * FROM FavoriteRecipe")
    fun getAllFavorites(): LiveData<List<FavoriteRecipe>>

    @Query("SELECT id FROM FavoriteRecipe")
    fun getFavoriteIds(): LiveData<List<Int>>

    @Query("SELECT id,title,category,photo_link FROM FavoriteRecipe")
    fun getFavoritePreview(): LiveData<List<PreviewRecipe>>

}