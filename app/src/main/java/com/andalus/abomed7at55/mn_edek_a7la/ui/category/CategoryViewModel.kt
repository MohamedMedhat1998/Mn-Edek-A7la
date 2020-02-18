package com.andalus.abomed7at55.mn_edek_a7la.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao

class CategoryViewModel(repositoryDao: RepositoryDao) : ViewModel() {

    private val _category = MutableLiveData<String>()
    val recipes = Transformations.switchMap(_category) {
        repositoryDao.getRecipeByCategory(it)
    }

    fun setCategory(category: String) {
        _category.value = category
    }

}