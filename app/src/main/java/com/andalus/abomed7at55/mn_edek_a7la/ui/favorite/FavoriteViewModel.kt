package com.andalus.abomed7at55.mn_edek_a7la.ui.favorite

import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.repositories.PrefsRecipeRepository
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

class FavoriteViewModel(private val favoriteRepository: PrefsRecipeRepository, private val prefsManager: PrefsManager<Int, Boolean>) : ViewModel() {

    val favoriteRecipes = favoriteRepository.getPreviewRecipes()

    init {
        favoriteRepository.subscribe()
    }

    fun deleteFromFavorite(id: Int) {
        prefsManager.setPrefsFile(Constants.FAVORITE_PREFS_FILE_NAME)
        prefsManager.invert(id)
        favoriteRepository.deleteRecipe(id)
    }

    override fun onCleared() {
        favoriteRepository.unSubscribe()
        super.onCleared()
    }
}