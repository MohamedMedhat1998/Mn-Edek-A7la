package com.andalus.abomed7at55.mn_edek_a7la.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.andalus.abomed7at55.mn_edek_a7la.data.FavoriteDao
import com.andalus.abomed7at55.mn_edek_a7la.data.RecipeDao
import com.andalus.abomed7at55.mn_edek_a7la.model.FavoriteRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class FavoriteRepositoryImpl(private val favoriteDao: FavoriteDao, private val prefsManager: PrefsManager<Int, Boolean>, private val recipeDao: RecipeDao) : FavoriteRepository, CoroutineScope {

    private val favoriteObserver = Observer<List<Int>> { currentIds ->
        val correctData = prefsManager.load()
        val toRemoveIds = mutableListOf<Int>()
        val toInsertIds = mutableListOf<Int>()
        var found = false

        currentIds.forEach current@{ current ->

            correctData.forEach correct@{ correct ->
                if (current == correct) {
                    found = true
                    return@correct
                }
            }

            if (!found)
                toRemoveIds.add(current)

            found = false
        }

        found = false

        correctData.forEach correct@{ correct ->

            currentIds.forEach current@{ current ->
                if (correct == current) {
                    found = true
                    return@current
                }
            }

            if (!found)
                toInsertIds.add(correct)

            found = false
        }

        val toInsertRecipes = mutableListOf<FavoriteRecipe>()

        recipeDao.getRecipesById(toInsertIds).observeForever {
            it.forEach { recipe ->
                toInsertRecipes.add(recipe.toFavoriteRecipe())
            }
            launch {
                favoriteDao.addToFavorite(toInsertRecipes)
            }
        }
        launch {
            favoriteDao.deleteFromFavorite(toRemoveIds)
        }

    }

    override fun subscribe() {
        favoriteDao.getFavoriteIds().observeForever(favoriteObserver)
    }

    override fun getFavoritePreview(): LiveData<List<PreviewRecipe>> {
        return favoriteDao.getFavoritePreview()
    }

    override fun unSubscribe() {
        favoriteDao.getFavoriteIds().removeObserver(favoriteObserver)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}