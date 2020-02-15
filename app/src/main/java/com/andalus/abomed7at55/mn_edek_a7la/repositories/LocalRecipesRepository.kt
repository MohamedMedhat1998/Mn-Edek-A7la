package com.andalus.abomed7at55.mn_edek_a7la.repositories

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.data.RecipeDao
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

class LocalRecipesRepository(private val dao: RecipeDao) : RepositoryDao {

    override fun getPreviewRecipes(): LiveData<List<PreviewRecipe>> {
        return dao.previewRecipes()
    }

    override fun getRecipeById(id: Int): LiveData<Recipe> {
        return dao.getRecipeById(id)
    }

    override suspend fun setFavoriteForRecipe(id: Int, currentState: Boolean) {
        dao.setFavoriteForRecipe(id, !currentState)
    }

}