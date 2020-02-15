package com.andalus.abomed7at55.mn_edek_a7la.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT id,title,category,photo_link,is_favorite FROM Recipe")
    fun previewRecipes(): LiveData<List<PreviewRecipe>>

    @Query("SELECT * FROM Recipe WHERE id = :id ")
    fun getRecipeById(id: Int): LiveData<Recipe>

    @Query("UPDATE Recipe SET is_favorite = :isFavorite WHERE id = :id")
    suspend fun setFavoriteForRecipe(id: Int, isFavorite: Boolean)
}