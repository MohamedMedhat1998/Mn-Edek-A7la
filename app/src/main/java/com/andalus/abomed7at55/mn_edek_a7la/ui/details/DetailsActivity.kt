package com.andalus.abomed7at55.mn_edek_a7la.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.item_tag.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel: DetailsViewModel by viewModel()
    private lateinit var recipe: Recipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            detailsViewModel.loadRecipeDetails(intent.extras!!.getInt(Constants.RECIPE_ID_KEY))
        }

        detailsViewModel.recipe.observe(this, Observer { recipe ->
            this.recipe = recipe
            val tags = recipe.category.split(',')
            loadTags(tags)
            tvRecipeTitle.text = recipe.title
            tvRecipeIngredients.text = recipe.ingredients
            tvRecipeSteps.text = recipe.steps
            btnWatchVideo.setOnClickListener {
                startActivity(Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(recipe.videoLink)
                })
            }
            Glide.with(this).load(recipe.photoLink).placeholder(R.drawable.placeholder).into(ivRecipeImage)
        })
        if (intent.hasExtra(Constants.SOURCE_KEY)) {
            if (intent.extras!!.getString(Constants.SOURCE_KEY) == Constants.SOURCE_FAVORITE) {
                ibLove.visibility = View.INVISIBLE
            }
        } else {
            detailsViewModel.isFavorite.observe(this, Observer {
                Toast.makeText(this, "observed, $it", Toast.LENGTH_SHORT).show()
                if (it)
                    ibLove.setImageResource(R.drawable.ic_heart_solid)
                else
                    ibLove.setImageResource(R.drawable.ic_heart)
            })
            ibLove.setOnClickListener {
                if (::recipe.isInitialized) {
                    detailsViewModel.switchFavoriteRecipes(recipe.id)
                }
            }
        }
    }

    private fun loadTags(tags: List<String>) {
        tagsHolder.removeAllViews()
        tags.reversed().forEach { title ->
            val inflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val tag = inflater.inflate(R.layout.item_tag, null)
            tag.tvTagName.text = getString(resources.getIdentifier(title, "string", packageName))
            tag.setOnClickListener {
                //TODO open category details
                startActivity(Intent(this, MainActivity::class.java).apply { putExtra("tag", title) })
            }
            tagsHolder.addView(tag)
        }
    }

}
