package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.CategoriesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.model.Category
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        setupCategories()
    }

    //TODO fix performance issue
    private fun setupCategories() {

        val categories = mutableListOf<Category>()
        val recent = Category(getString(R.string.recent), R.drawable.nav_recent, mutableListOf())
        val meat = Category(getString(R.string.meat), R.drawable.chicken_hot_icon, mutableListOf())
        val fish = Category(getString(R.string.fish), R.drawable.fish_icon, mutableListOf())
        val sweet = Category(getString(R.string.sweet), R.drawable.cake_icon, mutableListOf())
        val drink = Category(getString(R.string.drink), R.drawable.juice_icon, mutableListOf())
        val starches = Category(getString(R.string.starches), R.drawable.rice_icon, mutableListOf())
        val appetizer = Category(getString(R.string.appetizer), R.drawable.snack_icon, mutableListOf())
        val idea = Category(getString(R.string.idea), R.drawable.ligh_bulb_icon, mutableListOf())
        val diet = Category(getString(R.string.diet), R.drawable.diet_icon, mutableListOf())
        val bakery = Category(getString(R.string.bakery), R.drawable.bread_icon, mutableListOf())
        val milk = Category(getString(R.string.milk), R.drawable.milk_bottle_icon, mutableListOf())

        categories.add(recent)
        categories.add(meat)
        categories.add(fish)
        categories.add(sweet)
        categories.add(drink)
        categories.add(starches)
        categories.add(appetizer)
        categories.add(idea)
        categories.add(diet)
        categories.add(bakery)
        categories.add(milk)

        val categoriesAdapter = CategoriesAdapter(categories)
        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = categoriesAdapter

        mainViewModel.meatData.observe(this, Observer {
            meat.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.fishData.observe(this, Observer {
            fish.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.sweetData.observe(this, Observer {
            sweet.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.drinkData.observe(this, Observer {
            drink.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.starchesData.observe(this, Observer {
            starches.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.appetizerData.observe(this, Observer {
            appetizer.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.ideaData.observe(this, Observer {
            idea.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.dietData.observe(this, Observer {
            diet.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.bakeryData.observe(this, Observer {
            bakery.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
        mainViewModel.milkData.observe(this, Observer {
            milk.data = it
            categoriesAdapter.notifyDataSetChanged()
        })

    }


}