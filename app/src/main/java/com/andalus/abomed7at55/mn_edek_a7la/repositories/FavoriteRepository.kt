package com.andalus.abomed7at55.mn_edek_a7la.repositories

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.FavoriteRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe

interface FavoriteRepository {

    fun subscribe()

    fun getFavoritePreview(): LiveData<List<PreviewRecipe>>

    fun deleteFromFavorite(id:Int)

    fun unSubscribe()

}