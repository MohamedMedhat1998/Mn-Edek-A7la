package com.andalus.abomed7at55.mn_edek_a7la.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.repositories.recipe.RecipeRepository

class CategoryViewModel(private val recipeRepository: RecipeRepository) : ViewModel() {

    private val _category = MutableLiveData<String>()
    val recipes = Transformations.switchMap(_category) {
        recipeRepository.getRecipeByCategory(it)
    }

    fun setCategory(category: String) {
        _category.value = category
    }

    fun setFavoriteRecipe(id: Int, isFavorite: Boolean): Boolean {
        return recipeRepository.setFavoriteRecipe(id, isFavorite)
    }

    fun setLaterRecipe(id: Int, isLater: Boolean): Boolean {
        return recipeRepository.setLaterRecipe(id, isLater)
    }

}