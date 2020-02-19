package com.andalus.abomed7at55.mn_edek_a7la.ui.later

import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.repositories.PrefsRecipeRepository
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

class LaterViewModel(private val laterRepository: PrefsRecipeRepository, private val prefsManager: PrefsManager<Int, Boolean>) : ViewModel() {

    val laterRecipes = laterRepository.getPreviewRecipes()

    init {
        laterRepository.subscribe()
    }

    fun deleteFromLater(id: Int) {
        prefsManager.setPrefsFile(Constants.LATER_PREFS_FILE_NAME)
        prefsManager.invert(id)
        laterRepository.deleteRecipe(id)
    }

    override fun onCleared() {
        laterRepository.unSubscribe()
        super.onCleared()
    }

}