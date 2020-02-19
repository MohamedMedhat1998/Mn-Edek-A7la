package com.andalus.abomed7at55.mn_edek_a7la.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.repositories.recipe.RecipeRepository

class SearchViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _keyword = MutableLiveData<String>()

    val recipes = Transformations.switchMap(_keyword) {
        recipeRepository.searchForRecipe(it)
    }

    fun search(keyword: String) {
        _keyword.value = keyword
    }

    fun setFavoriteRecipe(id: Int, isFavorite: Boolean): Boolean {
        return recipeRepository.setFavoriteRecipe(id, isFavorite)
    }

    fun setLaterRecipe(id: Int, isLater: Boolean): Boolean {
        return recipeRepository.setLaterRecipe(id, isLater)
    }
}