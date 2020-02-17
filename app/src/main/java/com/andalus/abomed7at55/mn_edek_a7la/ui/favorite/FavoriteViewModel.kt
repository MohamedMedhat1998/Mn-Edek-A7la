package com.andalus.abomed7at55.mn_edek_a7la.ui.favorite

import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.repositories.FavoriteRepository

class FavoriteViewModel(val favoriteRepository: FavoriteRepository, val prefsManager: PrefsManager<Int, Boolean>) : ViewModel() {

    val favoriteRecipes = favoriteRepository.getFavoritePreview()

    init {
        favoriteRepository.subscribe()
    }

    override fun onCleared() {
        favoriteRepository.unSubscribe()
        super.onCleared()
    }
}