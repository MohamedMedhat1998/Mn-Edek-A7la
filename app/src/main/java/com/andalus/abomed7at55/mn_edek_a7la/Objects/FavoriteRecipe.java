package com.andalus.abomed7at55.mn_edek_a7la.Objects;

import android.arch.persistence.room.Entity;

@Entity
public class FavoriteRecipe extends Recipe {

    public static final String FAVORITE_RECIPE_TAG = "favorite_tag";

    public FavoriteRecipe(int id, String title, String ingredients, String steps, String category, String photoLink, String videoLink) {
        super(id, title, ingredients, steps, category, photoLink, videoLink);
    }

}
