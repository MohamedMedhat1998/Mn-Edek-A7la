package com.andalus.abomed7at55.mn_edek_a7la.networking

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import retrofit2.Retrofit

class Networking(retrofit: Retrofit) : Api {

    private val recipesService = retrofit.create(RecipesService::class.java)

    override fun getUpdates(id: Int): LiveData<List<Recipe>> {
        val remoteRecipeCallback = RemoteRecipeCallback()
        recipesService.getUpdates(id).enqueue(remoteRecipeCallback)
        return remoteRecipeCallback.result
    }

    override fun getRecentRecipes(): LiveData<List<Recipe>> {
        val remoteRecipeCallback = RemoteRecipeCallback()
        recipesService.getRecentRecipes().enqueue(remoteRecipeCallback)
        return remoteRecipeCallback.result
    }

}