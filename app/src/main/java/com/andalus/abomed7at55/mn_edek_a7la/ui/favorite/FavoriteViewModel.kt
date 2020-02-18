package com.andalus.abomed7at55.mn_edek_a7la.ui.favorite

import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.repositories.FavoriteRepository
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

class FavoriteViewModel(private val favoriteRepository: FavoriteRepository, private val prefsManager: PrefsManager<Int, Boolean>) : ViewModel() {

    val favoriteRecipes = favoriteRepository.getFavoritePreview()

    init {
        favoriteRepository.subscribe()
    }

    fun deleteFromFavorite(id: Int) {
        prefsManager.setPrefsFile(Constants.FAVORITE_PREFS_FILE_NAME)
        prefsManager.invert(id)
        favoriteRepository.deleteFromFavorite(id)
    }

    override fun onCleared() {
        favoriteRepository.unSubscribe()
        super.onCleared()
    }
}