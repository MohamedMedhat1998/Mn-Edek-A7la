package com.andalus.abomed7at55.mn_edek_a7la.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RecipeRepository

class CategoryViewModel(recipeRepository: RecipeRepository) : ViewModel() {

    private val _category = MutableLiveData<String>()
    val recipes = Transformations.switchMap(_category) {
        recipeRepository.getRecipeByCategory(it)
    }

    fun setCategory(category: String) {
        _category.value = category
    }

}