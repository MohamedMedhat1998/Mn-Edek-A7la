package com.andalus.abomed7at55.mn_edek_a7la.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao

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
}