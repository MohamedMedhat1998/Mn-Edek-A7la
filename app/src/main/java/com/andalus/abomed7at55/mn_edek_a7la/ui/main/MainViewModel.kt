package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

private const val PREVIEW_LIMIT = 8

class MainViewModel(repository: RepositoryDao) : ViewModel() {

    private val recipes = repository.getPreviewRecipes()

    val meatData = MediatorLiveData<List<PreviewRecipe>>()
    val fishData = MediatorLiveData<List<PreviewRecipe>>()
    val sweetData = MediatorLiveData<List<PreviewRecipe>>()
    val drinkData = MediatorLiveData<List<PreviewRecipe>>()
    val starchesData = MediatorLiveData<List<PreviewRecipe>>()
    val appetizerData = MediatorLiveData<List<PreviewRecipe>>()
    val ideaData = MediatorLiveData<List<PreviewRecipe>>()
    val dietData = MediatorLiveData<List<PreviewRecipe>>()
    val bakeryData = MediatorLiveData<List<PreviewRecipe>>()
    val milkData = MediatorLiveData<List<PreviewRecipe>>()

    init {
        meatData.addSource(recipes, getObserverLambda(meatData, Constants.MEAT))
        fishData.addSource(recipes, getObserverLambda(fishData, Constants.FISH))
        sweetData.addSource(recipes, getObserverLambda(sweetData, Constants.SWEET))
        drinkData.addSource(recipes, getObserverLambda(drinkData, Constants.DRINK))
        starchesData.addSource(recipes, getObserverLambda(starchesData, Constants.STARCHES))
        appetizerData.addSource(recipes, getObserverLambda(appetizerData, Constants.APPETIZER))
        ideaData.addSource(recipes, getObserverLambda(ideaData, Constants.IDEA))
        dietData.addSource(recipes, getObserverLambda(dietData, Constants.DIET))
        bakeryData.addSource(recipes, getObserverLambda(bakeryData, Constants.BAKERY))
        milkData.addSource(recipes, getObserverLambda(milkData, Constants.MILK))
    }

    private fun getObserverLambda(liveData: MediatorLiveData<List<PreviewRecipe>>, category: String): (List<PreviewRecipe>) -> Unit {
        return {
            liveData.value = it.filter { recipe ->
                recipe.category.contains(category)
            }.takeLast(PREVIEW_LIMIT)
        }
    }

}
