package com.andalus.abomed7at55.mn_edek_a7la.repositories.recipe

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.data.RecipeDao
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

class LocalRecipeRepository(private val dao: RecipeDao, private val prefsManager: PrefsManager<Int, Boolean>) : RecipeRepository {

    override fun getPreviewRecipes(): LiveData<List<PreviewRecipe>> {
        return dao.previewRecipes()
    }

    override fun getRecipeById(id: Int): LiveData<Recipe> {
        return dao.getRecipeById(id)
    }

    override fun setFavoriteRecipe(id: Int, isFavorite: Boolean): Boolean {
        prefsManager.setPrefsFile(Constants.FAVORITE_PREFS_FILE_NAME)
        return prefsManager.save(id, isFavorite)
    }

    override fun setLaterRecipe(id: Int, isLater: Boolean): Boolean {
        prefsManager.setPrefsFile(Constants.LATER_PREFS_FILE_NAME)
        return prefsManager.save(id, isLater)
    }

    override fun getRecipeByCategory(category: String): LiveData<List<PreviewRecipe>> {
        return dao.getRecipeByCategory("%$category%")
    }

    override fun searchForRecipe(keyword: String): LiveData<List<PreviewRecipe>> {
        return dao.searchForRecipe("%$keyword%")
    }

}