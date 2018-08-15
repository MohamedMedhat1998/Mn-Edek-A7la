package com.andalus.abomed7at55.mn_edek_a7la.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {
    @Query("SELECT * FROM Recipe")
    List<Recipe> getAllRecipes();
}
