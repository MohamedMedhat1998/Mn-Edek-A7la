package com.andalus.abomed7at55.mn_edek_a7la.ui.category

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.RecipesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import kotlinx.android.synthetic.main.activity_category.*
import org.koin.android.viewmodel.ext.android.viewModel

//TODO change the title of this activity to the correct category name
class CategoryActivity : AppCompatActivity() {

    private val categoryViewModel: CategoryViewModel by viewModel()
    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        //TODO get the recipe from opening activity
        val category = intent.extras!!.getString(Constants.CATEGORY_KEY)

        recipesAdapter = RecipesAdapter(size = Constants.SIZE_LARGE, onClick = {
            startActivity(Intent(this, DetailsActivity::class.java).apply { putExtra(Constants.RECIPE_ID_KEY, it) })
        }, onOptionsClicked = { id, button ->
            //TODO create the popup menu like the one in the main activity
        })
        rvCategory.adapter = recipesAdapter
        rvCategory.layoutManager = LinearLayoutManager(this)

        categoryViewModel.recipes.observe(this, Observer {
            recipesAdapter.data = it
            recipesAdapter.notifyDataSetChanged()
        })

        categoryViewModel.setCategory(category!!)
    }
}
