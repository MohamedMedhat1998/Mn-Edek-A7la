package com.andalus.abomed7at55.mn_edek_a7la.model

import android.view.View
import com.andalus.abomed7at55.mn_edek_a7la.adapters.RecipesAdapter

data class Category(val title: String, val iconId: Int,
                    private val onClick: (id: Int) -> Unit = {},
                    private val onOptionsClicked: (id: Int, optionsButton: View) -> Unit = { _, _ -> }) {

    var data: List<PreviewRecipe> = listOf()
        set(value) {
            field = value
            recipesAdapter.data = field
            recipesAdapter.notifyDataSetChanged()
        }

    val recipesAdapter = RecipesAdapter(data = data,onClick = onClick,onOptionsClicked =  onOptionsClicked)

}