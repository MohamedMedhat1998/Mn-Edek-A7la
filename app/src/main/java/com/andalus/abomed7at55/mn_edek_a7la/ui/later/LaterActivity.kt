package com.andalus.abomed7at55.mn_edek_a7la.ui.later

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
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
import kotlinx.android.synthetic.main.activity_later.*
import kotlinx.android.synthetic.main.activity_later.tvNoItems
import org.koin.android.viewmodel.ext.android.viewModel

class LaterActivity : AppCompatActivity() {

    private val laterViewModel: LaterViewModel by viewModel()

    private lateinit var recipesAdapter: RecipesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_later)

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar?.setCustomView(R.layout.action_bar_right_gravity)
        supportActionBar?.customView?.tvTitle?.text = getString(R.string.watch_later)


        recipesAdapter = RecipesAdapter(size = Constants.SIZE_LARGE, onClick = {
            startActivity(Intent(this, DetailsActivity::class.java).apply { putExtra(Constants.RECIPE_ID_KEY, it) })
        }, onOptionsClicked = { id, button ->
            val optionsPopup = PopupMenu(this, button)
            optionsPopup.menuInflater.inflate(R.menu.menu_later_options, optionsPopup.menu)
            optionsPopup.setOnMenuItemClickListener {
                if (it.itemId == R.id.delete_from_later) {
                    laterViewModel.deleteFromLater(id)
                }
                true
            }
            optionsPopup.show()
        })


        rvLaterRecipes.adapter = recipesAdapter
        rvLaterRecipes.layoutManager = LinearLayoutManager(this)

        laterViewModel.laterRecipes.observe(this, Observer {

            if (it.isEmpty())
                tvNoItems.visibility = View.VISIBLE
            else
                tvNoItems.visibility = View.INVISIBLE

            recipesAdapter.data = it
            recipesAdapter.notifyDataSetChanged()
        })
    }
}
