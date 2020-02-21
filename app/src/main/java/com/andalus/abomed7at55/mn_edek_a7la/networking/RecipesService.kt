package com.andalus.abomed7at55.mn_edek_a7la.networking

import com.andalus.abomed7at55.mn_edek_a7la.model.RemoteRecipe
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipesService {

    @GET(".")
    fun getAllRecipes(): Call<List<RemoteRecipe>>

    @GET("recipe")
    fun getRecipeById(@Query("id") id: Int): Call<RemoteRecipe>

    @GET("search")
    fun searchForRecipe(@Query("keyword") keyword: String): Call<List<RemoteRecipe>>

    @GET("{category}")
    fun getRecipesByCategory(@Path("category") category: String): Call<List<RemoteRecipe>>

    @GET("recent")
    fun getRecentRecipes(): Call<List<RemoteRecipe>>

    @GET("updates")
    fun getUpdates(@Query("id") id: Int): Call<List<RemoteRecipe>>

}