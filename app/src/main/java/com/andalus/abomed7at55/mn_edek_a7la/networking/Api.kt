package com.andalus.abomed7at55.mn_edek_a7la.networking

import androidx.lifecycle.LiveData
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe

interface Api {

    fun getUpdates(id: Int): LiveData<List<Recipe>>

    fun getRecentRecipes(): LiveData<List<Recipe>>

}