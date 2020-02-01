package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.CategoriesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.model.Category
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        fakeData()
    }

    fun fakeData() {
        val sweetRecipes = mutableListOf<Recipe>()
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        val category = Category("حلويات", R.drawable.cake_icon, sweetRecipes)
        val sweetRecipes2 = mutableListOf<Recipe>()
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        sweetRecipes2.add(Recipe(1, "حلويات", "A B C", "1 2 3", "sweet,meat", "http://www.fubiz.net/wp-content/uploads/2019/11/LOVELY-QUARANTINE-1.jpg", "www.youtube.com"))
        val category2 = Category("حلويات", R.drawable.cake_icon, sweetRecipes2)

        val data = mutableListOf<Category>()
        data.add(category)
        data.add(category2)
        val categoryAdapter = CategoriesAdapter(data)
        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = categoryAdapter
    }


}