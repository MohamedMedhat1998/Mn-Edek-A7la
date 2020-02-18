package com.andalus.abomed7at55.mn_edek_a7la.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

@Dao
interface RecipeDao {
    @Query("SELECT id,title,category,photo_link FROM Recipe")
    fun previewRecipes(): LiveData<List<PreviewRecipe>>

    @Query("SELECT * FROM Recipe WHERE id = :id ")
    fun getRecipeById(id: Int): LiveData<Recipe>

    @Query("SELECT id,title,category,photo_link FROM Recipe WHERE id in (:ids)")
    fun getPreviewRecipesByIds(ids: List<Int>): LiveData<List<PreviewRecipe>>

    @Query("SELECT * FROM Recipe WHERE id in (:ids)")
    fun getRecipesById(ids: List<Int>): LiveData<List<Recipe>>

    @Query("SELECT id,title,category,photo_link FROM Recipe WHERE category LIKE :category")
    fun getRecipeByCategory(category: String): LiveData<List<PreviewRecipe>>

}