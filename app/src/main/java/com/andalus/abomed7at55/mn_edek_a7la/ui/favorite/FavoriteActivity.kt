package com.andalus.abomed7at55.mn_edek_a7la.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.RecipesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import kotlinx.android.synthetic.main.activity_favorite.*
import org.koin.android.viewmodel.ext.android.viewModel

//TODO change the title of the activity
class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()

    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        recipesAdapter = RecipesAdapter(size = Constants.SIZE_LARGE
                , onClick = {
            startActivity(
                    Intent(this, DetailsActivity::class.java)
                            .apply {
                                putExtra(Constants.RECIPE_ID_KEY, it)
                                putExtra(Constants.SOURCE_KEY, Constants.SOURCE_FAVORITE)
                            })
        }, onOptionsClicked = { id, button ->
            val optionsPopup = PopupMenu(this, button)
            optionsPopup.menuInflater.inflate(R.menu.menu_favorite_options, optionsPopup.menu)
            optionsPopup.setOnMenuItemClickListener {
                if (it.itemId == R.id.delete_from_favorite) {
                    favoriteViewModel.deleteFromFavorite(id)
                }
                true
            }
            optionsPopup.show()
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
