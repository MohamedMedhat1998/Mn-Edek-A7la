package com.andalus.abomed7at55.mn_edek_a7la.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.andalus.abomed7at55.mn_edek_a7la.model.PreviewRecipe;
import com.andalus.abomed7at55.mn_edek_a7la.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT id,title,category,photo_link FROM Recipe")
    LiveData<List<PreviewRecipe>> getPreviewRecipes();

}
