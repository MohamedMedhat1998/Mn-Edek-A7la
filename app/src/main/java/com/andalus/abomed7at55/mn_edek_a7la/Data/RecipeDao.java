package com.andalus.abomed7at55.mn_edek_a7la.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.andalus.abomed7at55.mn_edek_a7la.Objects.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM Recipe WHERE category LIKE :category")
    List<Recipe> getRecipesByCategory(String category);

    @Query("SELECT * FROM Recipe WHERE title Like :searchKeyword OR ingredients LIKE :searchKeyword OR steps LIKE :searchKeyword")
    List<Recipe> getRecipesBySearchKeyword(String searchKeyword);

}
