package com.andalus.abomed7at55.mn_edek_a7la.repositories

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe

interface RepositoryDao {

    fun getPreviewRecipes(): LiveData<List<PreviewRecipe>>

}