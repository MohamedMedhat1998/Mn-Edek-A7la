package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.CategoriesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.ui.category.CategoryActivity
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsActivity
import com.andalus.abomed7at55.mn_edek_a7la.ui.favorite.FavoriteActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.viewmodel.ext.android.viewModel

//TODO fix api levels below 21
//TODO add navigation to the favorite activity
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favorite -> startActivity(Intent(this, FavoriteActivity::class.java))
            }
            true
        }



        categoriesAdapter = CategoriesAdapter(
                onCategoryClicked = {
                    startActivity(Intent(this, CategoryActivity::class.java).apply { putExtra(Constants.CATEGORY_KEY, it) })
                },
                onRecipeClicked = {
                    startActivity(Intent(this, DetailsActivity::class.java).apply { putExtra(Constants.RECIPE_ID_KEY, it) })
                }, onOptionsClicked = { id, optionsButton ->
            //mainViewModel.setFavoriteRecipe(id, currentState)
            Toast.makeText(this, "Options for recipe number $id is clicked", Toast.LENGTH_SHORT).show()

            val optionsPopup = PopupMenu(this, optionsButton)
            optionsPopup.menuInflater.inflate(R.menu.menu_recipe_options, optionsPopup.menu)
            optionsPopup.setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.add_to_favorite -> {
                        if (mainViewModel.setFavoriteRecipe(id, true))
                            Toast.makeText(this, getString(R.string.added_to_favorite_successfully), Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(this, getString(R.string.already_exists_in_favorite), Toast.LENGTH_SHORT).show()
                    }
                    R.id.add_to_later -> {
                        //TODO remove this fake navigation
                        Toast.makeText(this, "Added to later", Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            optionsPopup.show()

        })

        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.setItemViewCacheSize(11)
        rvCategories.adapter = categoriesAdapter

        mainViewModel.categories.observe(this, Observer {
            categoriesAdapter.data = it
            categoriesAdapter.notifyDataSetChanged()
        })

    }

}