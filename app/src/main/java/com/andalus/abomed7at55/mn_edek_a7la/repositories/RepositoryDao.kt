package com.andalus.abomed7at55.mn_edek_a7la.repositories

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

interface RepositoryDao {

    fun getPreviewRecipes(): LiveData<List<PreviewRecipe>>

    fun getRecipeById(id: Int): LiveData<Recipe>

    fun setFavoriteRecipe(id: Int, isFavorite: Boolean): Boolean

}