package com.andalus.abomed7at55.mn_edek_a7la.model

class RemoteRecipe(val id: Int,
                   val title: String,
                   val ingredients: String,
                   val steps: String,
                   val category: List<String>,
                   val photo_link: String,
                   val video_link: String) {

    fun toRecipe(): Recipe {
        val categoryBuilder = StringBuilder()
        category.forEach {
            categoryBuilder.append(it)
            categoryBuilder.append(',')
        }
        categoryBuilder.deleteCharAt(categoryBuilder.length - 1)
        val categoryString = categoryBuilder.toString()
        return Recipe(id, title, ingredients, steps, categoryString, photo_link, video_link)
    }

}