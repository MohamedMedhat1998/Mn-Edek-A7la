package com.andalus.abomed7at55.mn_edek_a7la.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.RecipesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import kotlinx.android.synthetic.main.action_bar_right_gravity.view.*
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.tvNoItems
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModel()
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val keyword = intent.extras!!.getString(Constants.SEARCH_KEYWORD)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_right_gravity)
        supportActionBar?.customView?.tvTitle?.text = getString(R.string.search_result_for,keyword)

        recipesAdapter = RecipesAdapter(size = Constants.SIZE_LARGE,
                onClick = {
                    startActivity(Intent(this, DetailsActivity::class.java).apply { putExtra(Constants.RECIPE_ID_KEY, it) })
                },
                onOptionsClicked = { id, optionsButton ->
                    val optionsPopup = PopupMenu(this, optionsButton)
                    optionsPopup.menuInflater.inflate(R.menu.menu_recipe_options, optionsPopup.menu)
                    optionsPopup.setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.add_to_favorite -> {
                                if (searchViewModel.setFavoriteRecipe(id, true))
                                    Toast.makeText(this, getString(R.string.added_to_favorite_successfully), Toast.LENGTH_SHORT).show()
                                else
                                    Toast.makeText(this, getString(R.string.already_exists_in_favorite), Toast.LENGTH_SHORT).show()
                            }
                            R.id.add_to_later -> {
                                if (searchViewModel.setLaterRecipe(id, true))
                                    Toast.makeText(this, getString(R.string.added_to_later_successfully), Toast.LENGTH_SHORT).show()
                                else
                                    Toast.makeText(this, getString(R.string.already_exists_in_later), Toast.LENGTH_SHORT).show()
                            }
                        }
                        true
                    }
                    optionsPopup.show()
                }
        )

        rvSearch.adapter = recipesAdapter
        rvSearch.layoutManager = LinearLayoutManager(this)

        searchViewModel.search(keyword!!)

        searchViewModel.recipes.observe(this, Observer {

            if (it.isEmpty())
                tvNoItems.visibility = View.VISIBLE
            else
                tvNoItems.visibility = View.INVISIBLE

            recipesAdapter.data = it
            recipesAdapter.notifyDataSetChanged()
        })

    }
}
