package com.andalus.abomed7at55.mn_edek_a7la.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.model.Category
import kotlinx.android.synthetic.main.item_category.view.*
import java.io.Serializable

class CategoriesAdapter(var data: List<Category> = listOf(), private val onRecipeClicked: (id: Int) -> Unit = {}) : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>(), Serializable {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.tvCategoryName.text = data[position].title
        holder.rvRecipes.adapter = RecipesAdapter(data[position].data){
            onRecipeClicked.invoke(it)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategoryName: TextView = itemView.tvCategoryName
        val rvRecipes: RecyclerView = itemView.rvRecipes

        init {
            rvRecipes.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
            rvRecipes.onFlingListener = null
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(rvRecipes)
        }
    }
}