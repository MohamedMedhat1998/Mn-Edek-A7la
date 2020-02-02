package com.andalus.abomed7at55.mn_edek_a7la.repositories

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.data.RecipeDao
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

class LocalRecipesRepository(private val dao: RecipeDao) : RepositoryDao {

    override fun getRecipesByCategories(category: String): LiveData<List<Recipe>> {
        return dao.getRecipesByCategory("%$category%")
    }

}