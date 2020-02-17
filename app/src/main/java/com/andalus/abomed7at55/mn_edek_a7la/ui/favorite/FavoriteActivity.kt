package com.andalus.abomed7at55.mn_edek_a7la.ui.favorite

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.RecipesAdapter
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        recipesAdapter = RecipesAdapter(onClick = {

        }, onOptionsClicked = { id, button ->

        })

        rvFavoriteRecipes.adapter = recipesAdapter
        rvFavoriteRecipes.layoutManager = LinearLayoutManager(this)

        favoriteViewModel.favoriteRecipes.observe(this, Observer {
            recipesAdapter.data = it
            recipesAdapter.notifyDataSetChanged()
            it.forEach { fav ->
                Log.d("OBSERVE", "${fav.id}: ${fav.title}")
            }
        })
    }
}
