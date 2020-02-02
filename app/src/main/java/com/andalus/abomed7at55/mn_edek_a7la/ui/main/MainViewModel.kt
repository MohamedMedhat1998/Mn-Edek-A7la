package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants

class MainViewModel(repository: RepositoryDao) : ViewModel() {

    val meatData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.MEAT)
    val fishData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.FISH)
    val sweetData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.SWEET)
    val drinkData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.DRINK)
    val starchesData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.STARCHES)
    val appetizerData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.APPETIZER)
    val ideaData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.IDEA)
    val dietData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.DIET)
    val bakeryData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.BAKERY)
    val milkData: LiveData<List<Recipe>> = repository.getRecipesByCategories(Constants.MILK)

}
