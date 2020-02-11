package com.andalus.abomed7at55.mn_edek_a7la.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import kotlinx.android.synthetic.main.item_tag.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val detailsViewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar)

        if (savedInstanceState == null) {
            detailsViewModel.loadRecipeDetails(intent.extras!!.getInt(Constants.RECIPE_ID_KEY))
        }

        detailsViewModel.recipe.observe(this, Observer { recipe ->
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

    }

    private fun loadTags(tags: List<String>) {
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
