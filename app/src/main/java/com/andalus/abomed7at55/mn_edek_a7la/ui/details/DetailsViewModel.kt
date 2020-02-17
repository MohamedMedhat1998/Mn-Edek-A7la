package com.andalus.abomed7at55.mn_edek_a7la.ui.details

import androidx.lifecycle.*
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: RepositoryDao) : ViewModel() {

    private var loaded = false

    private val _id = MutableLiveData<Int>()
    val recipe: LiveData<Recipe> = Transformations.switchMap(_id) {
        repository.getRecipeById(it)
    }

    fun loadRecipeDetails(id: Int) {
        if (!loaded) {
            _id.value = id
            loaded = true
        }
    }

    fun setFavoriteRecipes(id: Int, isFavorite: Boolean) {
        repository.setFavoriteRecipe(id, isFavorite)
    }
}