package com.andalus.abomed7at55.mn_edek_a7la.repositories

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

interface RepositoryDao {

    fun getRecipesByCategories(category: String): LiveData<List<Recipe>>

}