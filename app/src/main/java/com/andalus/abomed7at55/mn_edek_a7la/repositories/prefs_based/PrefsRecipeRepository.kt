package com.andalus.abomed7at55.mn_edek_a7la.repositories.prefs_based

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.FavoriteRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe

interface PrefsRecipeRepository {

    fun subscribe()

    fun getPreviewRecipes(): LiveData<List<PreviewRecipe>>

    fun deleteRecipe(id:Int)

    fun unSubscribe()

}