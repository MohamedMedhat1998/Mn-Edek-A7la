package com.andalus.abomed7at55.mn_edek_a7la.repositories.prefs_based

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.andalus.abomed7at55.mn_edek_a7la.data.LaterDao
import com.andalus.abomed7at55.mn_edek_a7la.data.RecipeDao
import com.andalus.abomed7at55.mn_edek_a7la.model.LaterRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.prefs.PrefsManager
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class LaterReposirory(private val laterDao: LaterDao,private val prefsManager: PrefsManager<Int,Boolean>,private val recipeDao: RecipeDao) : PrefsRecipeRepository, CoroutineScope {

    private val laterObserver = Observer<List<Int>> { currentIds ->
        prefsManager.setPrefsFile(Constants.LATER_PREFS_FILE_NAME)
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

        val toInsertRecipes = mutableListOf<LaterRecipe>()

        recipeDao.getRecipesById(toInsertIds).observeForever {
            it.forEach { recipe ->
                toInsertRecipes.add(recipe.toLaterRecipe())
            }
            launch {
                laterDao.addToLater(toInsertRecipes)
            }
        }
        launch {
            laterDao.deleteFromLater(toRemoveIds)
        }

    }

    override fun subscribe() {
        laterDao.getLaterIds().observeForever(laterObserver)
    }

    override fun getPreviewRecipes(): LiveData<List<PreviewRecipe>> {
        return laterDao.getLaterPreview()
    }

    override fun deleteRecipe(id: Int) {
        launch {
            laterDao.deleteFromLater(id)
        }
    }

    override fun unSubscribe() {
        laterDao.getLaterIds().removeObserver(laterObserver)
    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}