package com.andalus.abomed7at55.mn_edek_a7la.sync

import com.andalus.abomed7at55.mn_edek_a7la.networking.Api
import com.andalus.abomed7at55.mn_edek_a7la.repositories.recipe.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SynchronizerImpl(private val api: Api, private val repository: RecipeRepository) : Synchronizer, CoroutineScope {


    override fun sync() {
        repository.getLastId().observeForever { id ->
            api.getUpdates(id).observeForever { data ->
                launch {
                    repository.insert(data)
                }
            }
        }
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

}