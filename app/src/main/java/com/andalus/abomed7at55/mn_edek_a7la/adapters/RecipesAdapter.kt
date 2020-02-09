package com.andalus.abomed7at55.mn_edek_a7la.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andalus.abomed7at55.mn_edek_a7la.R
import com.andalus.abomed7at55.mn_edek_a7la.custom.FontAwesomeTextView
import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipesAdapter(private val data: List<PreviewRecipe>, private val onClick: (id: Int) -> Unit = {}) : RecyclerView.Adapter<RecipesAdapter.RecipeHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        return RecipeHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeHolder, position: Int) {
        Glide.with(context).load(data[position].photoLink).placeholder(R.drawable.placeholder).into(holder.ivRecipeImage)
        holder.tvRecipeTitle.text = data[position].title
        val categories = data[position].category.split(',')
        val stringBuilder = StringBuilder()
        categories.forEach {
            stringBuilder.append(context.getString(context.resources.getIdentifier(it, "string", context.packageName)))
            stringBuilder.append(',')
        }
        stringBuilder.deleteCharAt(stringBuilder.length - 1)
        holder.tvRecipeCategories.text = stringBuilder.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecipeHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivRecipeImage: ImageView = itemView.ivRecipeImage
        val tvRecipeTitle: TextView = itemView.tvRecipeTitle
        val tvRecipeCategories: TextView = itemView.tvRecipeCategories

        init {
            itemView.setOnClickListener {
                onClick.invoke(data[adapterPosition].id)
            }
        }

    }
}