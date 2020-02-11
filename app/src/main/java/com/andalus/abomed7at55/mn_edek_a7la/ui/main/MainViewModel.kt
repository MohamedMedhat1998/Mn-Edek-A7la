package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.model.Category
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.repositories.RepositoryDao
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants


class MainViewModel(repository: RepositoryDao, context: Context) : ViewModel() {

    private val dumb = MutableLiveData<Int>()
    private val recipes = switchMap(dumb) {
        repository.getPreviewRecipes()
    }

    val categories = MediatorLiveData<List<Category>>()

    //TODO implement recent recipes
    /*val recent = Category(context.getString(R.string.recent), R.drawable.nav_recent, mutableListOf())*/
    init {
        categories.addSource(recipes) {

            val meat = Category(context.getString(R.string.meat), R.drawable.chicken_hot_icon)
            val fish = Category(context.getString(R.string.fish), R.drawable.fish_icon)
            val sweet = Category(context.getString(R.string.sweet), R.drawable.cake_icon)
            val drink = Category(context.getString(R.string.drink), R.drawable.juice_icon)
            val starches = Category(context.getString(R.string.starches), R.drawable.rice_icon)
            val appetizer = Category(context.getString(R.string.appetizer), R.drawable.snack_icon)
            val idea = Category(context.getString(R.string.idea), R.drawable.ligh_bulb_icon)
            val diet = Category(context.getString(R.string.diet), R.drawable.diet_icon)
            val bakery = Category(context.getString(R.string.bakery), R.drawable.bread_icon)
            val milk = Category(context.getString(R.string.milk), R.drawable.milk_bottle_icon)

            meat.data = it.filterData(Constants.MEAT)
            fish.data = it.filterData(Constants.FISH)
            sweet.data = it.filterData(Constants.SWEET)
            drink.data = it.filterData(Constants.DRINK)
            starches.data = it.filterData(Constants.STARCHES)
            appetizer.data = it.filterData(Constants.APPETIZER)
            idea.data = it.filterData(Constants.IDEA)
            diet.data = it.filterData(Constants.DIET)
            bakery.data = it.filterData(Constants.BAKERY)
            milk.data = it.filterData(Constants.MILK)

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
        return this.filter { previewRecipe -> previewRecipe.category.contains(category) }.takeLast(Constants.PREVIEW_LIMIT)
    }

}
