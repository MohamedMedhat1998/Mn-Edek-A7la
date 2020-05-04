package com.andalus.abomed7at55.mn_edek_a7la.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.model.Category
import kotlinx.android.synthetic.main.item_category.view.*
import java.io.Serializable

class CategoriesAdapter(
        private val onCategoryClicked: (category: String) -> Unit = {},
        var data: List<Category> = listOf(),
        private val onRecipeClicked: (id: Int) -> Unit = {},
        private val onOptionsClicked: (id: Int, optionsButton: View) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.CategoryHolder>(), Serializable {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        if (data[position].title == context.getString(R.string.recent)) {
            holder.btnViewAll.visibility = View.INVISIBLE
        }

        holder.tvCategoryName.text = data[position].title

        data[position].recipesAdapter.onClick = {
            onRecipeClicked.invoke(it)
        }

        data[position].recipesAdapter.onOptionsClicked = { id, optionsButton ->
            onOptionsClicked.invoke(id, optionsButton)
        }

        holder.rvRecipes.adapter = data[position].recipesAdapter

    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvCategoryName: TextView = itemView.tvCategoryName
        val rvRecipes: RecyclerView = itemView.rvRecipes
        val btnViewAll: Button = itemView.btnViewAll

        init {
            rvRecipes.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
            rvRecipes.onFlingListener = null
            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(rvRecipes)
            btnViewAll.setOnClickListener {
                onCategoryClicked.invoke(data[adapterPosition].tag)
            }
        }
    }
}