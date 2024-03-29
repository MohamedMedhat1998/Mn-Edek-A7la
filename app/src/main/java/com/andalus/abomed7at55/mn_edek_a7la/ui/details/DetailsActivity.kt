package com.andalus.abomed7at55.mn_edek_a7la.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.ui.category.CategoryActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants.STRING_RESOURCE
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

        updateAppbarHeight()

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

    private fun updateAppbarHeight() {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val height = displayMetrics.heightPixels

        val coordinatorLayoutParams = app_bar.layoutParams as CoordinatorLayout.LayoutParams
        coordinatorLayoutParams.height = height / 2
    }

    private fun loadTags(tags: List<String>) {
        tagsHolder.removeAllViews()
        tags.reversed().forEach { title ->
            val inflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val tag = inflater.inflate(R.layout.item_tag, null)
            tag.tvTagName.text = getString(resources.getIdentifier(title, STRING_RESOURCE, packageName))
            tag.setOnClickListener {
                startActivity(Intent(this, CategoryActivity::class.java).apply { putExtra(Constants.CATEGORY_KEY, title) })
            }
            tagsHolder.addView(tag)
        }
    }

}
