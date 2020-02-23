package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.model.Category
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.repositories.recipe.RecipeRepository
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants


class MainViewModel(private val recipeRepository: RecipeRepository, context: Context) : ViewModel() {

    private val dumb = MutableLiveData<Int>()
    private val recipes = switchMap(dumb) {
        recipeRepository.getPreviewRecipes()
    }

    val categories = MediatorLiveData<List<Category>>()

    private val meat = Category(context.getString(R.string.meat), R.drawable.chicken_hot_icon, tag = Constants.MEAT)
    private val fish = Category(context.getString(R.string.fish), R.drawable.fish_icon, tag = Constants.FISH)
    private val sweet = Category(context.getString(R.string.sweet), R.drawable.cake_icon, tag = Constants.SWEET)
    private val drink = Category(context.getString(R.string.drink), R.drawable.juice_icon, tag = Constants.DRINK)
    private val starches = Category(context.getString(R.string.starches), R.drawable.rice_icon, tag = Constants.STARCHES)
    private val appetizer = Category(context.getString(R.string.appetizer), R.drawable.snack_icon, tag = Constants.APPETIZER)
    private val idea = Category(context.getString(R.string.idea), R.drawable.ligh_bulb_icon, tag = Constants.IDEA)
    private val diet = Category(context.getString(R.string.diet), R.drawable.diet_icon, tag = Constants.DIET)
    private val bakery = Category(context.getString(R.string.bakery), R.drawable.bread_icon, tag = Constants.BAKERY)
    private val milk = Category(context.getString(R.string.milk), R.drawable.milk_bottle_icon, tag = Constants.MILK)

    //TODO implement recent recipes
    /*val recent = Category(context.getString(R.string.recent), R.drawable.nav_recent, mutableListOf())*/
    init {
        categories.addSource(recipes) {
            meat.data = it.filterData(meat.tag)
            fish.data = it.filterData(fish.tag)
            sweet.data = it.filterData(sweet.tag)
            drink.data = it.filterData(drink.tag)
            starches.data = it.filterData(starches.tag)
            appetizer.data = it.filterData(appetizer.tag)
            idea.data = it.filterData(idea.tag)
            diet.data = it.filterData(diet.tag)
            bakery.data = it.filterData(bakery.tag)
            milk.data = it.filterData(milk.tag)

            val data: MutableList<Category> = mutableListOf()
            data.add(meat)
            data.add(fish)
            data.add(sweet)
            data.add(drink)
            data.add(starches)
            data.add(appetizer)
            data.add(idea)
            data.add(diet)
            data.add(bakery)
            data.add(milk)

            categories.value = data
        }

        dumb.value = 1
    }

    private fun List<PreviewRecipe>.filterData(category: String): List<PreviewRecipe> {
        return this.filter { previewRecipe -> previewRecipe.category.contains(category) }.take(Constants.PREVIEW_LIMIT)
    }

    fun setFavoriteRecipe(id: Int, isFavorite: Boolean): Boolean {
        return recipeRepository.setFavoriteRecipe(id, isFavorite)
    }

    fun setLaterRecipe(id: Int, isLater: Boolean): Boolean {
        return recipeRepository.setLaterRecipe(id, isLater)
    }

}
