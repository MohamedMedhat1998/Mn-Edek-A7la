package com.andalus.abomed7at55.mn_edek_a7la.ui.main

import android.content.Intent
import android.content.IntentFilter
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.adapters.CategoriesAdapter
import com.andalus.abomed7at55.mn_edek_a7la.broadcast_receivers.NetworkStateReceiver
import com.andalus.abomed7at55.mn_edek_a7la.ui.category.CategoryActivity
import com.andalus.abomed7at55.mn_edek_a7la.ui.details.DetailsActivity
import com.andalus.abomed7at55.mn_edek_a7la.ui.favorite.FavoriteActivity
import com.andalus.abomed7at55.mn_edek_a7la.ui.later.LaterActivity
import com.andalus.abomed7at55.mn_edek_a7la.ui.recent.RecentFragment
import com.andalus.abomed7at55.mn_edek_a7la.ui.search.SearchActivity
import com.andalus.abomed7at55.mn_edek_a7la.utils.Constants
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

const val CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"

//TODO fix api levels below 21
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    private lateinit var categoriesAdapter: CategoriesAdapter

    private val networkStateReceiver: NetworkStateReceiver by inject()

    private var connected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        networkStateReceiver.onNetworkStateReceive = {
            connected = it
            mainViewModel.setNetworkingState(it)
        }

        registerReceiver(networkStateReceiver, IntentFilter(CONNECTIVITY_ACTION))


        setupAds()

        setupNavigationDrawer()

        setupSearchBar()

        setupCategories()

        setupRecentRecipes()

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    override fun onDestroy() {
        unregisterReceiver(networkStateReceiver)
        super.onDestroy()
    }

    private fun setupAds() {
        MobileAds.initialize(applicationContext, getString(R.string.ADMOB_APP_ID))
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
    }

    private fun setupNavigationDrawer() {
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_favorite -> startActivity(Intent(this, FavoriteActivity::class.java))
                R.id.nav_later -> startActivity(Intent(this, LaterActivity::class.java))
                R.id.nav_youtube -> startActivity(Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(Constants.YOUTUBE_SUBSCRIPTION_LINK)
                })
                R.id.nav_facebook_page -> startActivity(Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(Constants.FACEBOOK_PAGE_LINK)
                })
                R.id.nav_facebook_group -> startActivity(Intent().apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse(Constants.FACEBOOK_GROUP_LINK)
                })
                R.id.nav_review -> try {
                    startActivity(Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(Constants.REVIEW_LINK)
                    })
                } catch (e: Exception) {
                    startActivity(Intent().apply {
                        action = Intent.ACTION_VIEW
                        data = Uri.parse(Constants.APP_LINK)
                    })
                }
                R.id.nav_share -> startActivity(Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message, Constants.APP_LINK))
                    type = "text/plain"
                })
            }
            true
        }
    }

    private fun setupSearchBar() {
        ibSearch.setOnClickListener {
            if (etSearch.text.toString() == "")
                Toast.makeText(this, getString(R.string.please_enter_keyword), Toast.LENGTH_LONG).show()
            else
                startActivity(Intent(this, SearchActivity::class.java).apply { putExtra(Constants.SEARCH_KEYWORD, etSearch.text.toString()) })
        }
    }

    private fun setupCategories() {
        categoriesAdapter = CategoriesAdapter(
                onCategoryClicked = {
                    startActivity(Intent(this, CategoryActivity::class.java).apply { putExtra(Constants.CATEGORY_KEY, it) })
                },
                onRecipeClicked = {
                    startActivity(Intent(this, DetailsActivity::class.java).apply { putExtra(Constants.RECIPE_ID_KEY, it) })
                }, onOptionsClicked = { id, optionsButton ->

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
                        if (mainViewModel.setLaterRecipe(id, true))
                            Toast.makeText(this, getString(R.string.added_to_later_successfully), Toast.LENGTH_SHORT).show()
                        else
                            Toast.makeText(this, getString(R.string.already_exists_in_later), Toast.LENGTH_SHORT).show()
                    }
                }
                true
            }
            optionsPopup.show()

        })

        rvCategories.layoutManager = LinearLayoutManager(this)
        rvCategories.adapter = categoriesAdapter

        mainViewModel.categories.observe(this, Observer {
            categoriesAdapter.data = it
            categoriesAdapter.notifyDataSetChanged()
        })
    }

    private fun setupRecentRecipes() {
        val recentFragment = RecentFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frameRecentPlaceholder, recentFragment).commit()

        mainViewModel.isConnected.observe(this, Observer {
            recentFragment.setConnectionState(it)
        })

        mainViewModel.recent.observe(this, Observer {
            if(it.isNotEmpty()){
                recentFragment.setData(it)
            }
        })

    }

}