package com.andalus.abomed7at55.mn_edek_a7la.ui.details

import androidx.lifecycle.*
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao

class DetailsViewModel(private val repository: RepositoryDao, private val prefsManager: PrefsManager<Int, Boolean>) : ViewModel() {

    private var loaded = false

    private val _id = MutableLiveData<Int>()
    val recipe: LiveData<Recipe> = Transformations.switchMap(_id) {
        repository.getRecipeById(it)
    }

    val isFavorite = MediatorLiveData<Boolean>()

    init {
        isFavorite.addSource(recipe) {
            isFavorite.value = isFavorite(it.id)
        }
    }

    fun loadRecipeDetails(id: Int) {
        if (!loaded) {
            _id.value = id
            loaded = true
        }
    }

    fun switchFavoriteRecipes(id: Int) {
        repository.setFavoriteRecipe(id, prefsManager.get(id))
        this.isFavorite.value = prefsManager.invert(id)
    }

    private fun isFavorite(id: Int): Boolean {
        return prefsManager.get(id)
    }
}