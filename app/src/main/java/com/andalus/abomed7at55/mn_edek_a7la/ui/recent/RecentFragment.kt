package com.andalus.abomed7at55.mn_edek_a7la.ui.recent

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.RecipesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsActivity
import com.andalus.abomed7at55.mn_edek_a7la.ui.main.MainViewModel
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.recent_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class RecentFragment(private var data: MutableList<Recipe> = mutableListOf()) : Fragment() {

    private val mainViewModel: MainViewModel by viewModel()

    private var previewData = mutableListOf<PreviewRecipe>()
    private val adapter: RecipesAdapter = RecipesAdapter(data = previewData, onClick = {
        startActivity(Intent(activity, DetailsActivity::class.java).apply { putExtra(Constants.RECIPE_ID_KEY, it) })
    }, onOptionsClicked = { id, optionsButton ->

        val optionsPopup = PopupMenu(activity!!.applicationContext, optionsButton)
        optionsPopup.menuInflater.inflate(R.menu.menu_recipe_options, optionsPopup.menu)
        optionsPopup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.add_to_favorite -> {
                    if (mainViewModel.setFavoriteRecipe(id, true))
                        Toast.makeText(activity!!.applicationContext, getString(R.string.added_to_favorite_successfully), Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(activity!!.applicationContext, getString(R.string.already_exists_in_favorite), Toast.LENGTH_SHORT).show()
                }
                R.id.add_to_later -> {
                    if (mainViewModel.setLaterRecipe(id, true))
                        Toast.makeText(activity!!.applicationContext, getString(R.string.added_to_later_successfully), Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(activity!!.applicationContext, getString(R.string.already_exists_in_later), Toast.LENGTH_SHORT).show()
                }
            }
            true
        }
        optionsPopup.show()

    })
    private lateinit var tvNoInternet: TextView
    private lateinit var tvUpdating: TextView
    private lateinit var pbLoading: ProgressBar
    private lateinit var parentLayout: View
    private var networkState = false

    init {
        data.forEach {
            previewData.add(it.toPreviewRecipe())
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.recent_fragment, container, false)
        tvNoInternet = view.tvNoInternet
        tvUpdating = view.tvUpdating
        pbLoading = view.pbLoading
        parentLayout = activity!!.findViewById(android.R.id.content)
        populateData(view)
        return view
    }

    private fun populateData(view: View) {
        view.rvRecent.adapter = adapter
        view.rvRecent.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        view.rvRecent.onFlingListener = null
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(view.rvRecent)
    }

    fun setData(data: List<Recipe>) {
        this.data.clear()
        this.data.addAll(data)
        Log.d("DATA", "${data.size}")
        previewData.clear()
        this.data.forEach {
            previewData.add(it.toPreviewRecipe())
        }
        adapter.notifyDataSetChanged()
        hideLoadingIndicator()
        if (networkState)
            showUpdatedState()
    }

    fun setConnectionState(connectionState: Boolean) {
        networkState = connectionState
        if (connectionState) {
            showLoadingIndicator()
            hideNetworkWarning()
        } else {
            showNetworkWarning()
        }
    }

    private fun showNetworkWarning() {
        tvNoInternet.visibility = View.VISIBLE
        hideLoadingIndicator()
    }

    private fun hideNetworkWarning() {
        tvNoInternet.visibility = View.INVISIBLE
    }

    private fun showLoadingIndicator() {
        tvUpdating.visibility = View.VISIBLE
        pbLoading.visibility = View.VISIBLE
    }

    private fun hideLoadingIndicator() {
        tvUpdating.visibility = View.INVISIBLE
        pbLoading.visibility = View.INVISIBLE
    }

    private fun showUpdatedState() {
        val snack by lazy {
            Snackbar.make(parentLayout, getString(R.string.successfully_updated), Snackbar.LENGTH_LONG)
                    .also {
                        val view: View = it.view
                        val params = view.layoutParams as FrameLayout.LayoutParams
                        params.gravity = Gravity.TOP
                        params.topMargin = 56
                        view.layoutParams = params
                    }
        }
        snack.show()
    }
}
